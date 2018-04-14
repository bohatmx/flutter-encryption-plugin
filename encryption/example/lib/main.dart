
import 'package:flutter/material.dart';
import 'package:encryption/encryption.dart';


void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  final String data = 'Running on:';
  @override
  _MyAppState createState() => new _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _password, _string, _encrypted, _decrypted;

  @override
  initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Encryption Plugin',
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text('Encryption Plugin Example'),
        ),
        body: Padding(
          padding: const EdgeInsets.all(16.0),
          child: new Card(
            elevation: 8.0,
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: new Column(
                children: <Widget>[
                  new TextField(
                    decoration: new InputDecoration(labelText: 'Password'),
                    onChanged: _passwdChanged,
                  ),
                  new TextField(
                    decoration:
                        new InputDecoration(labelText: 'StringToEncrypt'),
                    onChanged: _stringChanged,
                  ),
                  new Padding(
                    padding: const EdgeInsets.only(top: 38.0),
                    child: new RaisedButton(
                      elevation: 8.0,
                      onPressed: _onPressed,
                      child: new Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: new Text('Click to Encrypt and decrypt'),
                      ),
                    ),
                  ),
                  new Padding(
                    padding: const EdgeInsets.only(top:18.0),
                    child: new Text(
                      _encrypted == null ? '' : _encrypted,
                      style: new TextStyle(fontWeight: FontWeight.bold),
                    ),
                  ),
                  new Padding(
                    padding: const EdgeInsets.only(top:18.0),
                    child: new Text(
                      _decrypted == null ? '' : _decrypted,
                      style: new TextStyle(fontWeight: FontWeight.bold, color: Colors.teal),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  void _passwdChanged(String value) {
    _password = value;
  }

  void _stringChanged(String value) {
    _string = value;
  }

 _onPressed() async {
    if (_password == null) return null;
    if (_string == null) return null;
    print('###################### _encrypt #####################');
    _encrypted = await Encryption.getEncryptedString(_password, _string);

    print('###################### _decrypt #####################');
    _decrypted = await Encryption.getDecryptedString(_password, _encrypted);
    assert(_string == _decrypted);
    setState(() {});
  }
}
