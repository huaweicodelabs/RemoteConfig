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

package org.apache.cordova.agconnect.remoteconfig;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.remoteconfig.AGCConfigException;
import com.huawei.agconnect.remoteconfig.AGConnectConfig;
import com.huawei.agconnect.remoteconfig.ConfigValues;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * This class echoes a string called from JavaScript.
 */
public class AGCConfigPlugin extends CordovaPlugin {
    @Override
    protected void pluginInitialize() {
        if (AGConnectInstance.getInstance() == null) {
            final Context context = this.cordova.getActivity().getApplicationContext();
            AGConnectInstance.initialize(context);
        }
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("applyDefault".equals(action)) {
            this.applyDefault(callbackContext, args.getJSONObject(0));
            return true;
        } else if ("applyLastFetched".equals(action)) {
            this.applyLastFetched(callbackContext);
            return true;
        } else if ("fetch".equals(action)) {
            if (args.length() > 0) {
                this.fetch(callbackContext, args.getLong(0));
            } else {
                this.fetch(callbackContext);
            }
            return true;
        } else if ("getValue".equals(action)) {
            this.getValue(callbackContext, args.getString(0));
            return true;
        } else if ("getSource".equals(action)) {
            this.getSource(callbackContext, args.getString(0));
            return true;
        } else if ("getMergedAll".equals(action)) {
            this.getMergedAll(callbackContext);
            return true;
        } else if ("clearAll".equals(action)) {
            this.clearAll(callbackContext);
            return true;
        } else if ("setDeveloperMode".equals(action)) {
            this.setDeveloperMode(callbackContext, args.getBoolean(0));
            return true;
        } else {
            return false;
        }
    }

    private void applyDefault(CallbackContext callbackContext, final JSONObject defaults) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Log.d("AGC_LOG", "func applyDefault");
                    Map<String, Object> map = defaultsToMap(defaults);
                    AGConnectConfig.getInstance().applyDefault(map);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private static Map<String, Object> defaultsToMap(JSONObject object) throws JSONException {
        final Map<String, Object> map = new HashMap<String, Object>();

        for (Iterator<String> keys = object.keys(); keys.hasNext(); ) {
            String key = keys.next();
            Object value = object.get(key);

            if (value instanceof Integer) {
                value = new Long((Integer) value);
            }

            map.put(key, value);
        }
        return map;
    }

    private void applyLastFetched(CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Log.d("AGC_LOG", "func applyLastFetched");
                    ConfigValues configValues = AGConnectConfig.getInstance().loadLastFetched();
                    AGConnectConfig.getInstance().apply(configValues);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void fetch(CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Log.d("AGC_LOG", "func fetch 0");
                fetch(callbackContext, AGConnectConfig.getInstance().fetch());               
            }
        });
    }

    private void fetch(CallbackContext callbackContext, long intervalSeconds) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Log.d("AGC_LOG", "func fetch, intervalSeconds=" + Long.toString(intervalSeconds));
                fetch(callbackContext, AGConnectConfig.getInstance().fetch(intervalSeconds));               
            }
        });
    }

    private void fetch(CallbackContext callbackContext, final Task<ConfigValues> task) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    task.addOnSuccessListener(new OnSuccessListener<ConfigValues>() {
                        @Override
                        public void onSuccess(ConfigValues configValues) {
                            callbackContext.success();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            fetchFailProc(callbackContext, e);
                        }
                    });
                } catch (Exception e) {
                    fetchFailProc(callbackContext, e);
                }
            }
        });
    }

    private void fetchFailProc(CallbackContext callbackContext, final Exception e) {
        try {
            JSONObject returnResults = new JSONObject();
            String message = e.getMessage();
            long throttleEndTimeMillis = 0;
            if (e instanceof AGCConfigException) {
                throttleEndTimeMillis = ((AGCConfigException) e).getThrottleEndTimeMillis();
            }

            returnResults.put("throttleEndTimeMillis", throttleEndTimeMillis);
            returnResults.put("message", message);

            callbackContext.error(returnResults);
        } catch (JSONException ex) {
            callbackContext.error(ex.getMessage());
        }
    }

    private void getValue(CallbackContext callbackContext, String key) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Log.d("AGC_LOG", "func getValueAsString, key=" + key);
                    String value = AGConnectConfig.getInstance().getValueAsString(key);
                    callbackContext.success(value);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void getSource(CallbackContext callbackContext, String key) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Log.d("AGC_LOG", "func getSource, key=" + key);
                    AGConnectConfig.SOURCE source = AGConnectConfig.getInstance().getSource(key);
                    callbackContext.success(source.ordinal());
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void getMergedAll(CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Map<String, Object> map = AGConnectConfig.getInstance().getMergedAll();
                    JSONObject object = new JSONObject();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        object.put(entry.getKey(), entry.getValue());
                    }

                    Log.d("AGC_LOG", "func getMergedAll");
                    Log.d("AGC_LOG", object.toString());
                    callbackContext.success(object);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void clearAll(CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Log.d("AGC_LOG", "func clearAll");
                    AGConnectConfig.getInstance().clearAll();
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setDeveloperMode(CallbackContext callbackContext, boolean isDeveloperMode) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Log.d("AGC_LOG", "func setDeveloperMode");
                    AGConnectConfig.getInstance().setDeveloperMode(isDeveloperMode);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }
}
