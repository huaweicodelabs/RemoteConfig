cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-agc-config.remoteconfig",
      "file": "plugins/cordova-plugin-agc-config/www/AGCConfigPlugin.js",
      "pluginId": "cordova-plugin-agc-config",
      "clobbers": [
        "AGCConfigPlugin"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-agc-config": "1.1.0"
  };
});