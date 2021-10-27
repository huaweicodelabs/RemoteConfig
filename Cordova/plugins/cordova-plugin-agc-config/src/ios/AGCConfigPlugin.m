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

#import "AGCConfigPlugin.h"
#import <Cordova/CDV.h>
#import <AGConnectRemoteConfig/AGConnectRemoteConfig.h>

@implementation AGCConfigPlugin

- (void)applyDefault:(CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSLog(@"func applyDefault");
        NSDictionary<NSString*, id> *defaults = [command.arguments objectAtIndex:0];
        [[AGCRemoteConfig sharedInstance] applyDefaults:defaults];

        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)applyLastFetched:(CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSLog(@"func applyLastFetched");
        AGCConfigValues *values = [[AGCRemoteConfig sharedInstance] loadLastFetched];
        [[AGCRemoteConfig sharedInstance] apply: values];

        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)fetch:(CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSLog(@"func fetch");
        if ([command.arguments count] > 0) {
            NSInteger intervalSeconds = [[command.arguments objectAtIndex:0] integerValue];
            HMFTask<AGCConfigValues *> *task = [[AGCRemoteConfig sharedInstance] fetch:intervalSeconds];
            [[task addOnSuccessCallback:^(AGCConfigValues * _Nullable result) {
                [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK] callbackId:command.callbackId];
            }] addOnFailureCallback:^(NSError * _Nonnull error) {
                NSInteger timeMillis = 0;
                if ([error isKindOfClass:AGCRemoteConfigError.class]) {
                    AGCRemoteConfigError *configError = (AGCRemoteConfigError *)error;
                    timeMillis = (NSInteger)(configError.throttleEndTime * 1000);
                }
                NSString *message = error.localizedDescription;
                NSDictionary *result = @{
                    @"throttleEndTimeMillis": [NSString stringWithFormat:@"%ld",(long)timeMillis],
                    @"message": message
                };
                CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsDictionary:result];
                [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
            }];
        } else {
            HMFTask<AGCConfigValues *> *task = [[AGCRemoteConfig sharedInstance] fetch];
            [[task addOnSuccessCallback:^(AGCConfigValues * _Nullable result) {
                [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK] callbackId:command.callbackId];
            }] addOnFailureCallback:^(NSError * _Nonnull error) {
                NSInteger timeMillis = 0;
                if ([error isKindOfClass:AGCRemoteConfigError.class]) {
                    AGCRemoteConfigError *configError = (AGCRemoteConfigError *)error;
                    timeMillis = (NSInteger)(configError.throttleEndTime * 1000);
                }
                NSString *message = error.localizedDescription;
                NSDictionary *result = @{
                    @"throttleEndTimeMillis": [NSString stringWithFormat:@"%ld",(long)timeMillis],
                    @"message": message
                };
                CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsDictionary:result];
                [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
            }];
        }
    }];
}

- (void)getValue:(CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSString *key = [command.arguments objectAtIndex:0];
        NSLog(@"func getValue, key=%@", key);
        NSString *value = [[AGCRemoteConfig sharedInstance] valueAsString:key];

        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:value];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)getSource:(CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSString *key = [command.arguments objectAtIndex:0];
        NSLog(@"func getSource, key=%@", key);
        AGCRemoteConfigSource source = [[AGCRemoteConfig sharedInstance] getSource:key];

        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:(int)source];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)getMergedAll:(CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSLog(@"func getMergedAll");
        NSDictionary<NSString*, id> *result = [[AGCRemoteConfig sharedInstance] getMergedAll];

        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)clearAll:(CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSLog(@"func clearAll");
        [[AGCRemoteConfig sharedInstance] clearAll];

        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)setDeveloperMode:(CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSLog(@"The setDeveloperMode method only supports Android, please refer to the development guide to set the developer mode on iOS.");

        CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end
