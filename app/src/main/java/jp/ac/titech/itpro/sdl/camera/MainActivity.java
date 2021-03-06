package jp.ac.titech.itpro.sdl.camera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Bitmap photoImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button photoButton = findViewById(R.id.photo_button);
        photoButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            // TODO: You should setup appropriate parameters for the intent

            PackageManager manager = getPackageManager();
            @SuppressLint("QueryPermissionsNeeded")
            List<ResolveInfo> activities = manager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (!activities.isEmpty()) {
                launcher.launch(intent);
            } else {
                Toast.makeText(MainActivity.this, R.string.toast_no_activities, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showPhoto() {
        if (photoImage == null) {
            return;
        }
        ImageView photoView = findViewById(R.id.photo_view);
        photoView.setImageBitmap(photoImage);
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // TODO: You should implement the code that retrieve a bitmap image
                }
            });

    @Override
    protected void onResume() {
        super.onResume();
        showPhoto();
    }
}