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

exports.defineAutoTests = function () {
    describe('AGCConfigPlugin (cordova)', function () {
        it('AGCConfigPlugin should exist', function () {
            expect(AGCConfigPlugin).toBeDefined();
        });

        it('AGCConfigPlugin.applyDefault should exist', function () {
            expect(AGCConfigPlugin.applyDefault).toBeDefined();
        });

        it('AGCConfigPlugin.applyLastFetched should exist', function () {
            expect(AGCConfigPlugin.applyLastFetched).toBeDefined();
        });

        it('AGCConfigPlugin.fetch should exist', function () {
            expect(AGCConfigPlugin.fetch).toBeDefined();
        });

        it('AGCConfigPlugin.getValue should exist', function () {
            expect(AGCConfigPlugin.getValue).toBeDefined();
        });

        it('AGCConfigPlugin.getSource should exist', function () {
            expect(AGCConfigPlugin.getSource).toBeDefined();
        });

        it('AGCConfigPlugin.getMergedAll should exist', function () {
            expect(AGCConfigPlugin.getMergedAll).toBeDefined();
        });

        it('AGCConfigPlugin.clearAll should exist', function () {
            expect(AGCConfigPlugin.clearAll).toBeDefined();
        });
    });
};

exports.defineManualTests = function () {

};
