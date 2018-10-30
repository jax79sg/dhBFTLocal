/******************************************************************************
 *
 * 	 Copyright 2017, TRX Systems, Inc.  All Rights Reserved.
 *
 *   TRX Systems, Inc.
 *   7500 Greenway Center Drive, Suite 420
 *   Greenbelt, Maryland  20770
 *
 *   Tel:    (301) 313-0053
 *   email:  info@trxsystems.com
 *
 *****************************************************************************/
package com.trx.neon.model.building;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import java.util.ArrayList;

/**
 * A NeonFloorPlan contains a bitmap of the floor plan image,
 * georeferencing information for the four corners of that image
 * and the containing NeonBuildingFloor object
 */
public class NeonFloorPlan {

	/**
	 * Unique identifier for the floor plan image
	 */
	private final String ID;

	/**
	 * A bitmap of the floor plan image
	 */
	private final Bitmap Image;

	/**
	 * The NeonBuildingFloor that holds the
	 * floor plan image
	 */
	private final NeonBuildingFloor Floor;

	/**
	 * LatLong associated with the top left corner of the image
	 */
	private final LatLong TopLeft;
	/**
	 * LatLong associated with the top right corner of the image
	 */
	private final LatLong TopRight;
	/**
	 * LatLong associated with the bottom right corner of the image
	 */
	private final LatLong BottomRight;
	/**
	 * LatLong associated with the bottom left corner of the image
	 */
	private final LatLong BottomLeft;

	public NeonFloorPlan(Bitmap bitmap, NeonBuildingFloor floor) {

		if (floor.getFloorPlanCorners() == null || floor.getFloorPlanCorners().size() != 4 )
			throw new IllegalArgumentException("Must have four valid corners for a floor plan");
		this.ID = floor.getFloorPlanImageID();
		this.Image = bitmap;

		this.Floor = floor;
		this.TopLeft = floor.getFloorPlanCorners().get(3);
		this.TopRight = floor.getFloorPlanCorners().get(2);
		this.BottomRight = floor.getFloorPlanCorners().get(1);
		this.BottomLeft = floor.getFloorPlanCorners().get(0);
	}

	/**
	 * Get the unique ID for this floor
	 */
	public String getID() {return ID;}

	/**
	 * Retrieves the bitmap associated with this floor plan
	 * Don't forget to recycle this bitmap when you are done with it
	 */
	public Bitmap getBitmap() {
		return Image;
	}

	/**
	 * Helper function to mask the image with the NeonFloorOutline.
	 * This will take the floor plan image and set all areas
	 * outside the hull of the NeonFloorOutline and inside the holes
	 * of the NeonFloorOutline to transparent.  The result is a
	 * floor plan image clipped to the outline of the building.
	 */
	public Bitmap getBitmapClippedToFloor()
	{
		if (Image == null)
			return null;

		//Allocate space for what is effectively a stencil operation.
		Bitmap mask = Bitmap.createBitmap(Image.getWidth(), Image.getHeight(), Bitmap.Config.ARGB_8888);
		mask.eraseColor(Color.TRANSPARENT);

		Bitmap result = Bitmap.createBitmap(Image.getWidth(), Image.getHeight(), Bitmap.Config.ARGB_8888);
		result.eraseColor(Color.TRANSPARENT);

		//Convert floor points to texel coordinates on image.  This is not well defined for arbitrary quadrilaterals, (depends on triangulation when drawing)
		//But it should work fine for any rectangles/parallelograms.

		LatLong origin = getTopLeft();
		//Equirectangular Conversion -- latitude and longitude should be treated differently... but does it matter after change of basis?
		//double ratio = Math.cos(origin.Latitude / 180 * Math.PI);

		double[] X = new double[]{ getTopRight().Longitude - origin.Longitude, getTopRight().Latitude - origin.Latitude};
		double[] Y = new double[]{ getBottomLeft().Longitude - origin.Longitude, getBottomLeft().Latitude - origin.Latitude};

		//Convert LatLongs in building to (long, lat) -> (a,b) : aX + bY = (long, lat) - origin
		//Annoying without a perpendicular basis, but can be done by inverting a 2x2 matrix consisting of X and Y.
		//A Inv = [a b] = [X[0] Y[0]] Inv = 1 / (X[0]*Y[1] - Y[0]*X[1]) *	[Y[1] -Y[0]]
		//        [c d]   [X[1] Y[1]] 										[-X[1] X[0]]

		double determinant = 1 / (X[0] * Y[1] - Y[0] * X[1]);
		double a = Y[1] * determinant;
		double b = -Y[0] * determinant;
		double c = -X[1] * determinant;
		double d = X[0] * determinant;

		Canvas canvas = new Canvas(mask);

		Paint fill = new Paint(Paint.ANTI_ALIAS_FLAG);
		fill.setAntiAlias(true);
		fill.setStyle(Paint.Style.FILL);
		fill.setColor(Color.RED);

		Paint unfill = new Paint(Paint.ANTI_ALIAS_FLAG);
		unfill.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		unfill.setAntiAlias(true);
		unfill.setStyle(Paint.Style.FILL);
		unfill.setColor(Color.TRANSPARENT);

		NeonFloorOutline outline = Floor.getOutline();
		for (LatLongOutline polygonWithHoles : outline.Outlines)
		{
			ArrayList<LatLong> hull = polygonWithHoles.Hull;
			ArrayList<ArrayList<LatLong>> holes = polygonWithHoles.Holes;

			Path hullPath = new Path();
			for (int i = 0; i < hull.size(); i++)
			{
				LatLong hullPt = hull.get(i);

				double ptX = hullPt.Longitude - origin.Longitude;
				double ptY = hullPt.Latitude - origin.Latitude;

				double u = ptX * a + ptY * b;
				double v = ptX * c + ptY * d;

				u *= mask.getWidth();
				v *= mask.getHeight();

				if (i == 0)
					hullPath.moveTo((float)u, (float)v);
				else
					hullPath.lineTo((float)u, (float)v);

				if (i == hull.size() - 1)
					hullPath.close();
			}
			canvas.drawPath(hullPath, fill);

			for (ArrayList<LatLong> hole : holes)
			{
				Path holePath = new Path();
				for (int i = 0; i < hole.size(); i++)
				{
					LatLong holePt = hole.get(i);

					double ptX = holePt.Longitude - origin.Longitude;
					double ptY = holePt.Latitude - origin.Latitude;

					double u = ptX * a + ptY * b;
					double v = ptX * c + ptY * d;

					u *= mask.getWidth();
					v *= mask.getHeight();

					if (i == 0)
						holePath.moveTo((float)u, (float)v);
					else
						holePath.lineTo((float)u, (float)v);

					if (i == hole.size() - 1)
						holePath.close();
				}
				canvas.drawPath(holePath, unfill);
			}
		}

		Paint clip = new Paint(Paint.ANTI_ALIAS_FLAG);
		clip.setAntiAlias(true);
		clip.setStyle(Paint.Style.FILL);
		clip.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

		Canvas resultCanvas = new Canvas(result);
		resultCanvas.drawBitmap(getBitmap(), 0, 0, null);
		resultCanvas.drawBitmap(mask, 0, 0, clip);

		mask.recycle();

		return result;
	}


	public LatLong getTopLeft() {
		return TopLeft;
	}

	public LatLong getTopRight() {
		return TopRight;
	}

	public LatLong getBottomRight() {
		return BottomRight;
	}

	public LatLong getBottomLeft() {
		return BottomLeft;
	}
}