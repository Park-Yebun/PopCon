var admin = require("firebase-admin");

var serviceAccount = require("./ssafypopcon-firebase-adminsdk-cdwqh-cb14322ba0.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://ssafypopcon-default-rtdb.firebaseio.com"
});

// This registration token comes from the client FCM SDKs.
const registrationToken = 'dlhFqU1UnKZ40cStCcQXAx:APA91bGm5EEDSPcHzlRKQH_DRx85eaIQcU3h2XayA8saBzyJAcP0hL_1nIDpcQ0qs53WIuv_dZ3SEIE9D3wUcaREHbE0ZBpdKwpmUv0dBYSjZmw8Ozx9dHWs9mXq3N-3JdB37x8hdAZ8';

const message = {
  notification:{
    title : "I am Admin",
    body: "Hi client"
  },webpush: {
    fcmOptions: {
      link: '/?breakingnews'
    }
  },
  token: registrationToken
};

// Send a message to the device corresponding to the provided
// registration token.
admin.messaging().send(message)
  .then((response) => {
    // Response is a message ID string.
    console.log('Successfully sent message:', response);
  })
  .catch((error) => {
    console.log('Error sending message:', error);
  });