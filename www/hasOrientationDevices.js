var HasOrientationDevicesPlugin = function() {};

HasOrientationDevicesPlugin.prototype.hasCompass = function(successCallback, errorCallback) {
    if (cordova.platformId === 'browser') {
    	successCallback(0);
    } else {
    	cordova.exec(successCallback, errorCallback, "HasOrientationDevicesPlugin", "hasCompass", []);
   }
};

HasOrientationDevicesPlugin.prototype.hasAccelerometer = function(successCallback, errorCallback) {
  if (cordova.platformId === 'browser') {
        successCallback(0);
    } else {
        cordova.exec(successCallback, errorCallback, "HasOrientationDevicesPlugin", "hasAccelerometer", []);
   }
};


HasOrientationDevicesPlugin.prototype.checkCompass = function(successCallback, errorCallback) {
    if (cordova.platformId === 'browser') {
        successCallback(0);
    } else {
        cordova.exec(successCallback, errorCallback, "HasOrientationDevicesPlugin", "checkCompass", []);
   }    
};

HasOrientationDevicesPlugin.prototype.checkAccelerometer = function(successCallback, errorCallback) { 
  if (cordova.platformId === 'browser') {
        successCallback(0);
    } else {
        cordova.exec(successCallback, errorCallback, "HasOrientationDevicesPlugin", "checkAccelerometer", []);
   }
};


var hasOrientationDevicesPlugin = new HasOrientationDevicesPlugin();
module.exports = hasOrientationDevicesPlugin;

