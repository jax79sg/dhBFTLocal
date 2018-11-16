var map, infoWindow;
var markers=Array();

function initRabbit(mqAddress, username, password, topic){
    // var ws = new WebSocket('ws://18.221.97.164:15674/ws');
    var ws = new WebSocket(mqAddress);
    var client = Stomp.over(ws);
    var on_connect = function() {
        console.log('connected');
        // topic="/topic/"+topic;
        id = client.subscribe(topic, function(d) {
            console.log(d.body);
            var message=(d.body).split(",")
            // console.log("Lat:" + message[0]);
            // console.log("Long:" + message[1]);
            // console.log("Alt:" + message[2]);
            // console.log("Bearing:" + message[3]);
            // console.log("WhoAmI:" + message[4]);
            updateTarget(message);
        }, { id: 'starboard' });
    };
    var on_error =  function(error) {
        console.log(error);
    };
    // client.connect('jax', 'password', on_connect, on_error, 'bfttracks');
    var headers = {
        login:username,
        passcode: password,
        host:'bfttracks'
        // additional header
    };
    client.connect(headers, on_connect, on_error);
}


// function initRabbit(mqAddress,username, password){
//
//     var amqp = require('amqplib/callback_api');
//
//     amqp.connect('amqp://jax79sg.hopto.org', function(err, conn) {
//         conn.createChannel(function(err, ch) {
//             var ex = 'bfttracksExchange';
//
//             ch.assertExchange(ex, 'fanout', {durable: false});
//
//             ch.assertQueue('', {exclusive: true}, function(err, q) {
//                 console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", q.queue);
//                 ch.bindQueue(q.queue, ex, '');
//
//                 ch.consume(q.queue, function(msg) {
//                     console.log(" [x] %s", msg.content.toString());
//                     var message=(msg.content.toString()).split(",")
//                     // console.log("Lat:" + message[0]);
//                     // console.log("Long:" + message[1]);
//                     // console.log("Alt:" + message[2]);
//                     // console.log("Bearing:" + message[3]);
//                     // console.log("WhoAmI:" + message[4]);
//                     updateTarget(message);
//                 }, {noAck: true});
//             });
//         });
//     });
// }

function androidToJSupdateLocation(trackerMessage)
{
    var message=(trackerMessage).split(",");
    updateTarget(message);
}

function updateTarget(message)
{
    var x = Number(message[0]);
    var y = Number(message[1]);
    var alt=parseFloat(message[2]);
    alt=alt-baselineHeightInMetres;
    var user=message[4];
    var action=message[5];
    var bearing = message[3];
    var found=false;
    for (i = 0 ; i< markers.length; i++)
    {
        console.log("markers[i]",markers[i]._tooltip._content.valueOf());
        if (markers[i]._tooltip._content.valueOf()==user){
            found=true;
            console.log("Removing marker: ",markers[i]);
            map0.removeLayer(markers[i]);
            markers.splice(i,1);


            var marker=null;
            if (action=='FORWARD'){
                marker = getCustomMarker(x, alt, 'walking', user, true, 0);
            }else {
                marker = getCustomMarker(x, alt, 'standing', user, true, 0);
            }

            // var marker = getCustomMarker(y, alt, 'walking', user, true, bearing);
            console.log("Adding marker: "+ user + " " + x + " " + alt);
            markers.push(marker);
            marker.addTo(map0);

            // console.debug('Removing marker');
            break;
        }
    }
    if (!found)
    {
        console.log("Received: " + message);
        console.log("Adding marker: "+ user + " " + x + " " + alt);
        var marker=null;
        if (action=='FORWARD'){
            marker = getCustomMarker(x, alt, 'walking', user, true, 0);
        }else {
            marker = getCustomMarker(x, alt, 'standing', user, true, 0);
        }
        // var marker = getCustomMarker(y, alt, 'walking', user, true, bearing);
        marker.addTo(map0);
        markers.push(marker);
    }
}

// var historicalOverlay;
//
// function initMap() {
//     map = new google.maps.Map(document.getElementById('map'), {
//         center: new google.maps.LatLng(1.280076, 103.816760),
//         zoom: 20
//     });
//
//     var imageBounds = {
//         north: 1.280076,
//         south: 1.279954142068339,
//         east:  103.816760,
//         west:  103.81639334552828
//     };
//
//     historicalOverlay = new google.maps.GroundOverlay('http://18.221.97.164/dh-bftwebmaps/dhlab.bmp',imageBounds);
//     historicalOverlay.setMap(map);
//
//     initRabbit();
//
// }