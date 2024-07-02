import 'package:flutter/material.dart';
import 'package:sampleproject/Model/piesni.dart';
import 'package:sampleproject/Screens/SongMenu.dart';
import 'package:sampleproject/Screens/SongDetailScreen.dart';

final ButtonStyle buttonPrimary = ElevatedButton.styleFrom(
  minimumSize: Size(70, 50),
  backgroundColor: Colors.orange,
  elevation: 10,
  shape: const RoundedRectangleBorder(
    borderRadius: BorderRadius.all(
      Radius.circular(25),
    )
  )
);
final ButtonStyle buttonDisabled = ElevatedButton.styleFrom(
    minimumSize: Size(70, 50),
    backgroundColor: Colors.grey,
    elevation: 10,
    shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.all(
          Radius.circular(25),
        )
    )
);