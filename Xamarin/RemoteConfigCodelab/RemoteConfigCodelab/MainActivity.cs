﻿/*
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
using System;

using Android.App;
using Android.Widget;

using Android.OS;
using Huawei.Agconnect.Remoteconfig;
using Huawei.Hmf.Tasks;
using AndroidX.AppCompat.App;

namespace RemoteConfigCodelab
{
    [Activity(Label = "@string/app_name", MainLauncher = true)]
    public class MainActivity : AppCompatActivity
    {
        public TextView welcome;
        private Button fetchButton;
        private Button clearButton;
        private AGConnectConfig Config = AGConnectConfig.Instance;

        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);
            SetContentView(Resource.Layout.activity_main);

            fetchButton = FindViewById<Button>(Resource.Id.fetch_apply);
            welcome = FindViewById<TextView>(Resource.Id.MainText);
            clearButton = FindViewById<Button>(Resource.Id.Clear);

            // apply default config
            Config.ApplyDefault(Resource.Xml.RemoteConfig);

            welcome.Text = Config.GetValueAsString("Greeting");

            fetchButton.Click += delegate { FetchandApply(); };
        }

        public void FetchandApply()
        {
            AGConnectConfig.Instance.Fetch().AddOnSuccessListener(new TaskListener(this)).AddOnFailureListener(new TaskListener(this));
        }

        public class TaskListener : Java.Lang.Object, IOnSuccessListener, IOnFailureListener
        {
            MainActivity Context;

            public TaskListener(MainActivity context)
            {

                this.Context = context;
            }
            public void OnSuccess(Java.Lang.Object result)
            {
                IConfigValues configValues = (IConfigValues)result;
                String greeting = configValues.GetValueAsString("Greeting");
            }

            public void OnFailure(Java.Lang.Exception e)
            {
                
            }
        }
    }


}


