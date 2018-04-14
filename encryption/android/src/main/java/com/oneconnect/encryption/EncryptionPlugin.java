package com.oneconnect.encryption;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * EncryptionPlugin
 */
public class EncryptionPlugin implements MethodCallHandler {
  /**
   * Plugin registration.
   */

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "encryption");
    final MethodChannel channel2 = new MethodChannel(registrar.messenger(), "decryption");
    channel.setMethodCallHandler(new EncryptionPlugin());
    channel2.setMethodCallHandler(new EncryptionPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
      try {

          ArrayList<String> parms = (ArrayList<String>) call.arguments;
          Log.i(TAG, "####  onMethodCall: arguments: " + call.arguments.toString());
          if (call.method.equals("encrypt")) {
              if (!parms.isEmpty()) {
                  String encrypted = encrypt(parms.get(0), parms.get(1));
                  Log.d(TAG, "onMethodCall: encrypted: " + encrypted);
                  result.success(encrypted);
              } else {
                  result.error("Error", "No parameters", null);
              }
              return;
          }
          if (call.method.equals("decrypt")) {
              if (!parms.isEmpty()) {
                  String decrypted = decrypt(parms.get(0), parms.get(1));
                  Log.d(TAG, "onMethodCall: decrypted: " + decrypted);
                  result.success(decrypted);
              } else {
                  result.error("Error", "No parameters", null);
              }
              return;
          }
          result.notImplemented();
      } catch (Exception e) {
          Log.d(TAG, "Exception onMethodCall: ", e);
          result.error("Error", "Encrypting or Decrypting", e);
      }
  }
  private static final String ALGORITHME = "Blowfish";
  private static final String TRANSFORMATION = "Blowfish/ECB/PKCS5Padding";
  private static final String CHARSET = "ISO-8859-1";

  public String encrypt(String password, String seed)
          throws NoSuchAlgorithmException,
          NoSuchPaddingException,
          InvalidKeyException,
          UnsupportedEncodingException,
          IllegalBlockSizeException,
          BadPaddingException
  {
      Log.d(TAG, "encrypt: " + seed);
    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(password.getBytes(CHARSET), ALGORITHME));
    return new String(cipher.doFinal(seed.getBytes()), CHARSET);
  }

  public String decrypt(String password, String encryptedSeed)
          throws NoSuchAlgorithmException,
          NoSuchPaddingException,
          InvalidKeyException,
          UnsupportedEncodingException,
          IllegalBlockSizeException,
          BadPaddingException
  {
      Log.d(TAG, "decrypt: " + encryptedSeed);
    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(password.getBytes(), ALGORITHME));
    return new String(cipher.doFinal(encryptedSeed.getBytes(CHARSET)), CHARSET);
  }

  public static final String TAG = EncryptionPlugin.class.getSimpleName();
}
