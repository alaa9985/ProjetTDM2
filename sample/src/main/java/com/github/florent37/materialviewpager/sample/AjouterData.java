package com.github.florent37.materialviewpager.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;

/**
 * Created by alaa eddine on 27/05/2017.
 */

public class AjouterData {

    public static int RESULT_LOAD_IMAGE = 1;
    public static int RESULT_LOAD_VIDEO=2;
    public static StorageReference mStorageRef;
    public static Uri file;
    public static Uri VideoFile;
    public static String type;

    public static String urlVideo;
    public static String urlImage;

    private DatabaseReference database;
    private Dossier dossier;


    public AjouterData() {


        Log.i("alaa", "AjouterData: ");

        FirebaseStorage storage = FirebaseStorage.getInstance();
        Log.i("alaa", "AjouterData1: ");
        mStorageRef = storage.getReferenceFromUrl("gs://projet-mobile-ec754.appspot.com/");
        Log.i("alaa", "AjouterData2: ");
    }





     public void  saveImage(final Context context,Uri uri)
        {



            Uri selectedImage = uri;
        Log.i("alaa", "AjouterData: "+uri.toString());
            MyIntentService.uriFile=selectedImage;
            file = selectedImage;
            urlImage=file.getLastPathSegment();
            try {
                MyIntentService.strm = ((Activity) context).getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(context, MyIntentService.class);
            intent.putExtra("uri", selectedImage);
            intent.putExtra("type","image");
            type="image";
        ((Activity) context).startService(intent);


        }
    public void  saveVideo(final Context context,Uri uri)
    {


            Uri selectedVideo = uri;
            MyIntentService.uriVideo=selectedVideo;

            VideoFile = selectedVideo;

            urlVideo=VideoFile.getLastPathSegment();

            try {
                MyIntentService.strmVideo= ((Activity) context).getContentResolver().openInputStream(selectedVideo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(context, MyIntentService.class);
            intent.putExtra("uri", selectedVideo);
            type="video";
            intent.putExtra("type","image");
        ((Activity) context).startService(intent);
    }





}
