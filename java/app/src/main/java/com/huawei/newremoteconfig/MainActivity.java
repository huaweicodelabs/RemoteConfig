/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.huawei.newremoteconfig;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.huawei.agconnect.remoteconfig.AGConnectConfig;
import com.huawei.agconnect.remoteconfig.ConfigValues;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private static final String GREETING_KEY = "GREETING_KEY";
    private static final String SET_BOLD_KEY = "SET_BOLD_KEY";
    private AGConnectConfig config;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.greeting);
        Button button = findViewById(R.id.fetch_button);
        config = AGConnectConfig.getInstance();

        config.applyDefault(R.xml.remote_config);
        textView.setText(config.getValueAsString(GREETING_KEY));
        Boolean isBold = config.getValueAsBoolean(SET_BOLD_KEY);
        if (isBold){
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAndApply();
            }
        });

    }
    private void fetchAndApply(){
        config.fetch(0).addOnSuccessListener(new OnSuccessListener<ConfigValues>() {
            @Override
            public void onSuccess(ConfigValues configValues) {
                // Apply Network Config to Current Config
                config.apply(configValues);
                updateUI();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                textView.setText("fetch setting failed: " + e.getMessage());
            }
        });
    }
    private void updateUI(){
        String text = config.getValueAsString(GREETING_KEY);
        Boolean isBold = config.getValueAsBoolean(SET_BOLD_KEY);
        textView.setText(text);
        if (isBold){
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }
}