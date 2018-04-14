# encryption

A Flutter plugin to encrypt and decrypt a string.

## Getting Started

First, add encryption as a dependency in your pubspec.yaml file.
Then import 'package:encryption/encryption.dart';

## Usage

    var _encrypted = await Encryption.getEncryptedString(_password, _string);

    var _decrypted = await Encryption.getDecryptedString(_password, _encrypted);



For help getting started with Flutter, view our online
[documentation](https://flutter.io/).

For help on editing plugin code, view the [documentation](https://flutter.io/platform-plugins/#edit-code).