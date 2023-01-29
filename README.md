# IPC Demo
Basic demonstration of IPC capabilities in Android OS using Binder.
There are client and server apps in the project, both of them use AIDL-defined interface called `IDemoService` to communicate.

## How it works
You can write a message in the server app and save it. Then this message will be displayed in the toast after pressing "Get Message" button in the client app.

## Screenshots
<img src="https://i.imgur.com/4x7cHP9.png" alt="Server app" width="25%" height="25%"> <img src="https://i.imgur.com/mmWGf8I.png" alt="Client app" width="25%" height="25%">