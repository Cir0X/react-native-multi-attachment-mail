
# react-native-react-native-multi-attachment-mail

## Getting started

`$ npm install react-native-react-native-multi-attachment-mail --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-multi-attachment-mail`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-react-native-multi-attachment-mail` and add `RNReactNativeMultiAttachmentMail.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNReactNativeMultiAttachmentMail.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeMultiAttachmentMailPackage;` to the imports at the top of the file
  - Add `new RNReactNativeMultiAttachmentMailPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-multi-attachment-mail'
  	project(':react-native-react-native-multi-attachment-mail').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-multi-attachment-mail/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-multi-attachment-mail')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNReactNativeMultiAttachmentMail.sln` in `node_modules/react-native-react-native-multi-attachment-mail/windows/RNReactNativeMultiAttachmentMail.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using React.Native.Multi.Attachment.Mail.RNReactNativeMultiAttachmentMail;` to the usings at the top of the file
  - Add `new RNReactNativeMultiAttachmentMailPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNReactNativeMultiAttachmentMail from 'react-native-react-native-multi-attachment-mail';

// TODO: What to do with the module?
RNReactNativeMultiAttachmentMail;
```
  