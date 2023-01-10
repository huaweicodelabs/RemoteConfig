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
<template>
  <div class="remoteconfigContainer">
    <el-form :model="dataForm_sdk" :rules="rules" status-icon label-position="left" label-width="80px"
             class="demo-ruleForm remoteconfig-page">
      <h3 class="title">RemoteConfigCodelab</h3>
      <el-form-item label="FetchConfig" label-width="200px" class="fetchConfigTitle">
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetch" class="fetchButton">Fetch</el-button>
        <el-button type="primary" @click="applyConfig" class="applyConfigButton">Apply</el-button>
      </el-form-item>
      <el-form-item label="DefaultConfig" label-width="200px" class="setDefaultVauleTitle">
      </el-form-item>
      <el-form-item>
        <el-input type="textarea" :disabled="false" v-model="dataForm_sdk.defaultConfig"
                  placeholder="Please enter JSON format string"
                  class="inputValueText"></el-input>
      </el-form-item>
      <el-button type="primary" @click="applyDefault" class="applyDefaultButton">applyDefault</el-button>
      <el-form-item label="GetVaule" class="getVauleTitle">
      </el-form-item>
      <el-form-item>
        <el-input type="textarea" :disabled="false" autosize="autosize" v-model="dataForm_sdk.valueText"
                  class="getValueText"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getValue" class="getValueButton">Show Value</el-button>
        <el-button @click="clearAll" class="clearValueButton">ClearAll</el-button>
      </el-form-item>
      <el-form-item>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import agconnect from '@agconnect/api'
import '@agconnect/remoteconfig'
import '@agconnect/instance'

export default {
  name: 'remoteconfig',
  data () {
    return {
      dataForm_sdk: {
        valueText: '',
        defaultConfig: ''
      },
      rules: {},
    };
  },
  async created () {
    var agConnectConfig =
      {

      };
    agconnect.instance().configInstance(agConnectConfig);
  },
  methods: {
    async fetch() {
      agconnect.remoteConfig().minFetchIntervalMillis = 1000
      await agconnect.remoteConfig().fetch().then((res) => {
        alert('fetch successfully!');
      }).catch((err) => {
        alert(JSON.stringify(err));
      });
    },
    applyConfig() {
      return agconnect.remoteConfig().apply().then((res) => {
            if (res) {
              alert('apply successfully!');
            } else {
              alert('apply failed!');
            }
          }
        ).catch(error => {
          alert(JSON.stringify(error));
        });
    },
    applyDefault() {
      if (this.dataForm_sdk.defaultConfig != '') {
        if (typeof this.dataForm_sdk.defaultConfig == 'string') {
          try {
            var defaultConfigJson = JSON.parse(this.dataForm_sdk.defaultConfig);
            if (typeof defaultConfigJson == 'object' && defaultConfigJson) {
              let defaultConfigMap = new Map();
              for (var k in defaultConfigJson) {
                defaultConfigMap.set(k, JSON.stringify(defaultConfigJson[k]));
              }
              agconnect.remoteConfig().applyDefault(defaultConfigMap);
              alert('applyDefault success');
            } else {
              alert('please input JSON string!');
              this.dataForm_sdk.defaultConfig = '';
            }
          } catch (e) {
            alert('please input JSON string!');
            this.dataForm_sdk.defaultConfig = '';
          }
        }
      } else {
        alert('please input JSON string!');
        this.dataForm_sdk.defaultConfig = '';
      }
    },
    getValue () {
      let obj = Object.create(null);
      let resultMap = agconnect.remoteConfig().getMergedAll();
      for (let [k, v] of resultMap) {
        obj[k] = v.value;
      }
      this.dataForm_sdk.valueText = JSON.stringify(obj);
    },
    clearAll() {
      agconnect.remoteConfig().clearAll().then(()=>{
        alert('clearAll OK');
      });
    }
  },
}
</script>

<style scoped>
.remoteconfig-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: auto;
  width: 800px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.getVauleTitle,
.fetchConfigTitle,
.setDefaultVauleTitle {
  border-bottom: 1px solid #eaeaea;
}

.applyConfigButton,
.fetchButton {
  width: 120px;
}

.getValueButton {
  margin-left: 30px;
  width: 120px;
}

.applyDefaultButton {
  margin-left: 530px;
  width: 120px;
}

.getValueText,
.inputValueText {
  width: 600px;
}
</style>
