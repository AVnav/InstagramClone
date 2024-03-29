package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instagramclone.fragments.ComposeFragment;
import com.example.instagramclone.fragments.PostsFragment;
import com.example.instagramclone.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class  MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 45;
//    private EditText etDescription;
//    private Button btnCaptureImage;
//    private ImageView ivPostImage;
//    private Button btnSubmit;
//    private Button btnLogout;
//    private File photoFile;
//    public String photoFileName = "photo.jpg";
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        etDescription = findViewById(R.id.etDescription);
//        btnCaptureImage = findViewById(R.id.btnCaptureImage);
//        ivPostImage = findViewById(R.id.ivPostImage);
//        btnSubmit = findViewById(R.id.btnSubmit);
//        btnLogout = findViewById(R.id.btnLogout);
        bottomNavigationView = findViewById((R.id.bottom_navigation));

            //queryPosts();
    //        btnSubmit.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View view) {
    //                String description = etDescription.getText().toString();
    //                if (description.isEmpty()) {
    //                    Toast.makeText(MainActivity.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
    //                    return;
    //                }
    //
    //                if(photoFile == null || ivPostImage.getDrawable() == null){
    //                    Toast.makeText(MainActivity.this, "There is no image", Toast.LENGTH_SHORT).show();
    //                    return;
    //                }
    //                ParseUser currentUser = ParseUser.getCurrentUser();
    //                savePost(description, currentUser, photoFile);
    //            }
    //        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment;
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            //fragment = fragment1;
                            //Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                            fragment = new PostsFragment();
                            break;
                        case R.id.action_compose:
                            //fragment = fragment2;
                            //Toast.makeText(MainActivity.this, "Compose", Toast.LENGTH_SHORT).show();
                            fragment = new ComposeFragment();
                            break;
                        case R.id.action_profile:
                        default:
                            //fragment = fragment3;
                            //Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                            fragment = new ProfileFragment();
                            break;
                    }
                    fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                    return true;
                }
            }
        );
        bottomNavigationView.setSelectedItemId(R.id.action_home);

//        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchCamera();
//            }
//        });
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseUser.logOut();
//                goLoginActivity();
//            }
//        });
    }

//    private void goLoginActivity() {
//        Intent i = new Intent(this, LoginActivity.class);
//        startActivity(i);
//        finish();
//    }
//
//    private void launchCamera() {
//        // create Intent to take a picture and return control to the calling application
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Create a File reference for future access
//        photoFile = getPhotoFileUri(photoFileName);
//        Log.i(TAG,"launching camera...");
//        // wrap File object into a content provider
//        // required for API >= 24
//        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
//        Uri fileProvider = FileProvider.getUriForFile(MainActivity.this, "com.codepath.fileprovider", photoFile);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
//
//        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
//        // So as long as the result is not null, it's safe to use the intent.
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            // Start the image capture intent to take photo
//            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // by this point we have the camera photo on disk
//                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
//                // RESIZE BITMAP, see section below
//                // Load the taken image into a preview
//                //ImageView ivPreview = (ImageView) findViewById(R.id.ivPreview);
//                ivPostImage.setImageBitmap(takenImage);
//            } else { // Result was a failure
//                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    // Returns the File for a photo stored on disk given the fileName
//    public File getPhotoFileUri(String fileName) {
//        // Get safe storage directory for photos
//        // Use `getExternalFilesDir` on Context to access package-specific directories.
//        // This way, we don't need to request external read/write runtime permissions.
//        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
//            Log.d(TAG, "failed to create directory");
//        }
//
//        // Return the file target for the photo based on filename
//        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);
//
//        return file;
//    }
//
//    private void savePost(String description, ParseUser currentUser, File photoFile) {                  //private initially
//        Post post = new Post();
//        post.setDescription(description);
//        post.setImage(new ParseFile(photoFile));
//        post.setUser(currentUser);
//        post.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e != null){
//                    Log.e(TAG, "Error while saving", e);
//                    Toast.makeText(MainActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
//                }
//                Log.i(TAG, "Post save was successful!");
//                etDescription.setText("");
//                ivPostImage.setImageResource(0);
//            }
//        });
//    }
//
//    private void queryPosts() {
//        // Specify which class to query
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null){
//                    Log.e(TAG, "Issue with getting posts", e);
//                    return;
//                }
//                for (Post post : posts){
//                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
//            }
//        });
//    }
}