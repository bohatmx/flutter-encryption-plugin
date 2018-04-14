import 'dart:async';
import 'dart:core';

import 'package:flutter/services.dart';

class Encryption {
  static const MethodChannel _channel = const MethodChannel('encryption');
  static const MethodChannel _channel2 = const MethodChannel('decryption');

  static Future<String> getEncryptedString(
      String password, String stringToEncrypt) async {
    List<String> parms = new List<String>();
    parms.add(password);
    parms.add(stringToEncrypt);
    final String encrypted = await _channel.invokeMethod('encrypt', parms);
    return encrypted;
  }

  static Future<String> getDecryptedString(
      String password, String stringToDecrypt) async {
    List<String> parms = new List<String>();
    parms.add(password);
    parms.add(stringToDecrypt);
    final String decrypted = await _channel2.invokeMethod('decrypt', parms);
    return decrypted;
  }
}
