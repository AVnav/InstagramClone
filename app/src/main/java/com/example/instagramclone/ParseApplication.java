package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(Post.class);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("WdrUcIUArj3T1p4AWRed7Z3BEpuRLR2M2BYuQstz")
                .clientKey("VEjAliOKUfbcSArjNZ1CQ0WBf6RWibr5OnfLucbg")
                .server("https://parseapi.back4app.com").build());
    }
}
