import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:sampleproject/Model/piesni.dart';
import 'package:photo_view/photo_view.dart';
import 'package:sampleproject/Screens/button.dart';
import 'package:sampleproject/Screens/SongDetailScreen.dart';
import 'package:sampleproject/main.dart';
import 'package:sampleproject/Screens/ProgramCreator.dart';
import 'package:sampleproject/Screens/ChoiceScreen.dart';

class PresentationScreen extends StatefulWidget{
  @override
  _PresentationScreen createState() => _PresentationScreen();
}
class _PresentationScreen extends State<PresentationScreen>
{

  final Song song = songList[0];
  @override
  Widget build(BuildContext context) {

    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Text("Plan mszy"),

      ),
      body: OrientationBuilder(
        builder: (context, orientation)
        {
          return Column(
            children: [
              Expanded(
                  child:ListView.builder(
                      itemCount: FinalList.length,
                      itemBuilder: (context, index) {
                        Song song = FinalList[index];
                        return Card(
                          child: ListTile(
                            title: Text(Kolejnosc1[index] + song.title),

                            trailing: Icon(Icons.arrow_forward_rounded),
                            onTap: () {
                              print(FinalList.length);
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => SongDetailsScreen(song)
                                  )
                              );

                            },
                          ),
                        );

                      })

              )

            ],
          );
        },

      ),
    );




  }

}