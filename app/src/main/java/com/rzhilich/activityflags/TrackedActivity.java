package com.rzhilich.activityflags;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created on 7/11/14.
 */
abstract class TrackedActivity extends Activity {

    public static final String EXTRA_INTENTS = "intents";

    private final String activityName = getClass().getSimpleName();
    private TextView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = (TextView) getLayoutInflater().inflate(R.layout.view_label, null);
        setContentView(contentView);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        final Intent initialIntent = getIntent();
        if (initialIntent.hasExtra(EXTRA_INTENTS)) {
            final List<Intent> intents = (List<Intent>) initialIntent.getSerializableExtra(EXTRA_INTENTS);
            if (!intents.isEmpty()) {
                final Intent intent = intents.remove(0);
                intent.putExtra(EXTRA_INTENTS, new ArrayList<Intent>(intents));
                startActivity(intent);
            }
        }
    }

    @Override
    @SuppressLint({"DefaultLocale", "InflateParams"})
    protected void onResume() {
        super.onResume();
        final int taskId = getTaskId();
        final String activityLabel = String.format("Task: %d, Activity: %s", taskId, activityName);
        contentView.setText(activityLabel);
    }
}
