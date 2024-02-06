// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here. Other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
firebase.initializeApp({
    apiKey: "AIzaSyDvNw-1CiYp-H2q_RTsM_uM778SsrWj2ZM",
    authDomain: "ssafypopcon.firebaseapp.com",
    projectId: "ssafypopcon",
    storageBucket: "ssafypopcon.appspot.com",
    messagingSenderId: "543956021805",
    appId: "1:543956021805:web:07b2670024d09e5ec9fea4",
    measurementId: "G-5QZKSRXRD2"
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
    console.log(
        '[firebase-messaging-sw.js] Received background message ',
        payload
    );
    // Customize notification here
    const notificationTitle = payload.notification.title;
    const notificationOptions = {
        body: playload.notification.body,
        icon: '/favicon-32x32.png'
    };

    self.registration.showNotification(notificationTitle, notificationOptions);
});