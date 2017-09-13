package kmitl.lab03.notthegit.SimpleMyDot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import kmitl.lab03.notthegit.SimpleMyDot.model.Dot;
import kmitl.lab03.notthegit.SimpleMyDot.model.Dots;
import kmitl.lab03.notthegit.SimpleMyDot.model.Colors;
import kmitl.lab03.notthegit.SimpleMyDot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener{
    private DotView dotView;
    private Dots dots;
    CallbackManager callbackManager;
    LoginButton loginButton;


    Bitmap mbitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView)findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        initializeControls();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                postimg();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    private void postimg() {
        //Bitmap image = bitmap1;
        //SharePhoto photo = new SharePhoto.Builder()
        //       .setBitmap(image)
        //        .build();
        //SharePhotoContent content = new SharePhotoContent.Builder()
        //        .addPhoto(photo)
        //        .build();

        //imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onRandomDot(View view){
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, new Colors().getColor());
        dots.addDot(newDot);
    }

    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();

    }

    public Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        return bitmap1;
    }


    public void createImage(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() +
                "/capturedscreenandroid.jpg");
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void onClean(View view){
        dots.clearAll();
    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }
    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if(dotPosition == -1) {
            Dot newDot = new Dot(x, y, 30, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }
}
