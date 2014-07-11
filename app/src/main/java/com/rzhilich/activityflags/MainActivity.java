package com.rzhilich.activityflags;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created on 7/11/14.
 */
public class MainActivity extends Activity {

    private static final int SEPARATE_TASK = Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent[] intents = new Intent[]{
                // Task 1
                new Intent(this, A.class) {
                    {
                        setFlags(SEPARATE_TASK);
                    }
                },
                new Intent(this, B.class),
                new Intent(this, C.class),
                new Intent(this, D.class),

                // Task 2
                new Intent(this, X.class) {
                    {
                        setFlags(SEPARATE_TASK);
                    }
                },
                new Intent(this, Y.class),
                new Intent(this, Z.class),

                // Flavor dependant
                new Intent(this, D.class),
        };

        // Allows to get rid of flashing screens
//        startActivities(intents);

        // Allows debugging
        final ArrayList<Intent> intentList = new ArrayList<Intent>(Arrays.asList(intents));
        final Intent intent = intentList.remove(0);
        intent.putExtra(TrackedActivity.EXTRA_INTENTS, intentList);
        startActivity(intent);

        finish();
    }
}
