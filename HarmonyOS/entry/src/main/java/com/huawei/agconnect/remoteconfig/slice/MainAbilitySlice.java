package com.huawei.agconnect.remoteconfig.slice;

import com.huawei.agconnect.remoteconfig.AGConnectConfig;
import com.huawei.agconnect.remoteconfig.ConfigValues;
import com.huawei.agconnect.remoteconfig.ResourceTable;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.text.Font;


public class  MainAbilitySlice extends AbilitySlice {
    private static final String GREETING_KEY = "GREETING_KEY";
    private static final String SET_BOLD_KEY = "SET_BOLD_KEY";
    private AGConnectConfig config;
    private Text text;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        text = (Text) findComponentById(ResourceTable.Id_greeting);
        Button button = (Button) findComponentById(ResourceTable.Id_fetch_button);
        config = AGConnectConfig.getInstance();

        config.applyDefault(ResourceTable.Profile_remote_config);
        text.setText(config.getValueAsString(GREETING_KEY));
        Boolean isBold = config.getValueAsBoolean(SET_BOLD_KEY);
        if (isBold){
            text.setFont(Font.DEFAULT_BOLD);
        }

        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                fetchAndApply();
            }
        });
    }

    private void fetchAndApply(){
        config.fetch(0).addOnSuccessListener(new OnSuccessListener<ConfigValues>() {
            @Override
            public void onSuccess(ConfigValues configValues) {
                config.apply(configValues);
                updateUI();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                text.setText("fetch setting failed: " + e.getMessage());
            }
        });
    }
    private void updateUI(){
        String greeting = config.getValueAsString(GREETING_KEY);
        Boolean isBold = config.getValueAsBoolean(SET_BOLD_KEY);
        text.setText(greeting);
        if (isBold){
            text.setFont(Font.DEFAULT_BOLD);
        }
    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
