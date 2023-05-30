var HasOrientationDevicesPlugin = function() {};

hasOrientationDevicesPlugin.prototype.hasCompass = function(successCallback, errorCallback) {
    if (cordova.platformId === 'browser') {
    	successCallback(0);
    } else {
    	cordova.exec(successCallback, errorCallback, "HasOrientationDevicesPlugin", "hasCompass", []);
   }
};

hasOrientationDevicesPlugin.prototype.hasAccelerometer = function(successCallback, errorCallback) {
  if (cordova.platformId === 'browser') {
        successCallback(0);
    } else {
        cordova.exec(successCallback, errorCallback, "HasOrientationDevicesPlugin", "hasAccelerometer", []);
   }
};


hasOrientationDevicesPlugin.prototype.checkCompass = function(successCallback, errorCallback) {
    if (cordova.platformId === 'browser') {
        successCallback(0);
    } else {
        cordova.exec(successCallback, errorCallback, "HasOrientationDevicesPlugin", "checkCompass", []);
   }    
};

hasOrientationDevicesPlugin.prototype.checkAccelerometer = function(successCallback, errorCallback) { 
  if (cordova.platformId === 'browser') {
        successCallback(0);
    } else {
        cordova.exec(successCallback, errorCallback, "HasOrientationDevicesPlugin", "checkAccelerometer", []);
   }
};


var hasOrientationDevicesPlugin = new HasOrientationDevicesPlugin();
module.exports = hasOrientationDevicesPlugin;

