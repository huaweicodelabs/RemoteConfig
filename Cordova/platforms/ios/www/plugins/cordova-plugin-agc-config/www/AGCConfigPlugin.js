cordova.define("cordova-plugin-agc-config.remoteconfig", function(require, exports, module) {
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

var exec = require('cordova/exec');

exports.applyDefault = function(defaults, success, error) {
    if (defaults != null) {
        for (var key in defaults) {
            defaults[key] = defaults[key].toString();
        }
    }
    exec(success, error, 'AGCConfigPlugin', 'applyDefault', [defaults]);
};

exports.applyLastFetched = function(success, error) {
    exec(success, error, 'AGCConfigPlugin', 'applyLastFetched', []);
};

exports.fetch = function(intervalSeconds, success, error) {
    var args = [];
    if (typeof intervalSeconds === 'number') {
        args.push(intervalSeconds);
    } else {
        error = success;
        success = intervalSeconds;
    }
    exec(success, error, 'AGCConfigPlugin', 'fetch', args);
};

exports.getValue = function(key, success, error) {
    exec(success, error, 'AGCConfigPlugin', 'getValue', [key]);
};

exports.getSource = function(key, success, error) {
    exec(success, error, 'AGCConfigPlugin', 'getSource', [key]);
};

exports.getMergedAll = function(success, error) {
    exec(success, error, 'AGCConfigPlugin', 'getMergedAll', []);
};

exports.clearAll = function(success, error) {
    exec(success, error, 'AGCConfigPlugin', 'clearAll', []);
};

exports.setDeveloperMode = function(isDeveloperMode, success, error) {
    exec(success, error, 'AGCConfigPlugin', 'setDeveloperMode', [isDeveloperMode]);
};
});
