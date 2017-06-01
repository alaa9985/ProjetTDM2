package com.github.florent37.materialviewpager.sample;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MyIntentService extends IntentService {


    public static Uri uriFile;
    public static InputStream strm;
    public static Uri uriVideo;
    public static InputStream strmVideo;


    FirebaseStorage storage = FirebaseStorage.getInstance();
    // Create a storage reference from our app
    StorageReference storageRef = storage.getReference("photos");
    StorageReference storageRef2 = storage.getReference("videos");


    //private DatabaseReference databaseReference ;
    //private FirebaseDatabase mFirebaseInstance;

    Notification noti;
    NotificationManager notificationManager;
    Notification.Builder builder;
    Integer notificationID = 1001;
    static int count = 0;

    public MyIntentService() {
        super("MyIntentService");


    }

    @Override
    protected void onHandleIntent(Intent intent) {
       String  type =intent.getStringExtra("type");


        switch (type)
        {
            case "image":
                try {
                    Log.i("alaa", "start");
                    PendingIntent pIntent = PendingIntent.getService(this, (int) System.currentTimeMillis(), intent, 0);
                    builder = new Notification.Builder(this)
                            .setContentTitle("Upload image")
                            //.setContentText("Notification when uploading")
                            .setSmallIcon(android.R.drawable.ic_menu_upload)
                            .setContentIntent(pIntent).setAutoCancel(true)
                            .setOngoing(true)
                            .setProgress(100, 0, false)
                            .setAutoCancel(true)
                    ;
                    noti = builder.build();
                    notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(notificationID, noti);


                    uploadOldPhoto();


                } catch (Exception ex) {

                }
                break;
            case "video":
                try {
                    Log.i("alaa", "start");
                    PendingIntent pIntent = PendingIntent.getService(this, (int) System.currentTimeMillis(), intent, 0);
                    builder = new Notification.Builder(this)
                            .setContentTitle("Upload video")
                            //.setContentText("Notification when uploading")
                            .setSmallIcon(android.R.drawable.ic_menu_upload)
                            .setContentIntent(pIntent).setAutoCancel(true)
                            .setOngoing(true)
                            .setProgress(100, 0, false)
                            .setAutoCancel(true)
                    ;
                    noti = builder.build();
                    notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(notificationID, noti);


                    uploadVideo();


                } catch (Exception ex) {

                }


        }
    }


    private void uploadOldPhoto() {

        int bytes = 0;
        try {
            bytes = inputToSize(strm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final int bytes_ = bytes;

        UploadTask uploadTask1 = storageRef.child(uriFile.getLastPathSegment()).putFile(uriFile);
        uploadTask1.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                count = count + 1;
                //double progress = (100.0 * count*256*1024*8) / bytes_;

                double progress = (100 * taskSnapshot.getBytesTransferred()) / bytes_;
                Log.i("alaa", "getBytesTransferred() is  " + taskSnapshot.getBytesTransferred() + " bytes");
                int currentprogress = (int) progress + 1;
                //progressBar.setProgress(currentprogress);
                builder.setProgress(100, currentprogress, false);
                builder.setContentTitle("Uploading image "+currentprogress+"%");
                Log.i("alaa", "(" + bytes_ + "%)Progress");
                noti = builder.build();
                notificationManager.notify(notificationID, noti);


            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


            @Override
            public void onSuccess( UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("alaa", "complete");
                builder.setProgress(100, 100, false);
                builder.setContentTitle("Completed");
                builder.setSmallIcon(android.R.drawable.stat_sys_upload_done);
                noti = builder.build();
                notificationManager.notify(notificationID, noti);

            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Log.i("alaa", "complete");
                builder.setProgress(100, 100, false);
                builder.setContentTitle("Completed");
                builder.setSmallIcon(android.R.drawable.stat_sys_upload_done);
                noti = builder.build();
                notificationManager.notify(notificationID, noti);

            }
        })






                .addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("alaa", "Paused");
                        builder.setContentTitle("Paused");
                        noti = builder.build();
                        notificationManager.notify(notificationID, noti);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("alaa", "failure");
                builder.setContentTitle("Failed");
                noti = builder.build();
                notificationManager.notify(notificationID, noti);
            }
        });


    }
    private void uploadVideo() {

        int bytes = 0;
        try {
            bytes = inputToSize(strmVideo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final int bytes_ = bytes;
        //

        UploadTask uploadTask1 = storageRef2.child(uriVideo.getLastPathSegment()).putFile(uriVideo);
        uploadTask1.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                count = count + 1;
                //double progress = (100.0 * count*256*1024*8) / bytes_;

                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                Log.i("alaa", "getBytesTransferred() is  " + taskSnapshot.getBytesTransferred() + " bytes");
                int currentprogress = (int) progress + 1;
                //progressBar.setProgress(currentprogress);
                builder.setProgress(100, currentprogress, false);
                builder.setContentTitle("Uploading video ");
                Log.i("alaa", "(" + bytes_ + "%)Progress");
                noti = builder.build();
                notificationManager.notify(notificationID, noti);


            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("alaa", "complete");
                builder.setProgress(100, 100, false);
                builder.setContentTitle("Completed");
                builder.setSmallIcon(android.R.drawable.stat_sys_upload_done);
                noti = builder.build();
                notificationManager.notify(notificationID, noti);
            }
        })
                .addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("alaa", "Paused");
                        builder.setContentTitle("Paused");
                        noti = builder.build();
                        notificationManager.notify(notificationID, noti);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("alaa", "failure");
                builder.setContentTitle("Failed");
                noti = builder.build();
                notificationManager.notify(notificationID, noti);
            }
        });


    }
    private int inputToSize(InputStream oldInput) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int b;
        while ((b = oldInput.read()) != -1)
            os.write(b);
        return os.toByteArray().length;
    }
}
