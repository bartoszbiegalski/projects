import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:sampleproject/Model/piesni.dart';
import 'package:photo_view/photo_view.dart';
import 'package:sampleproject/Screens/SongDetailScreen.dart';
import 'package:sampleproject/main.dart';
import 'package:sampleproject/Screens/button.dart';
import 'package:sampleproject/Screens/PresentationScreen.dart';
import 'package:sampleproject/Screens/SongMenu.dart';
import 'package:sampleproject/Screens/ProgramCreator.dart';


List <Song> FinalList = [
  Plan[0], currentMass[0],Plan[1],currentMass[1],currentMass[2],Plan[2], Plan[3],Plan[4],
];


class ChoiceScreen extends StatefulWidget{
  @override
  _ChoiceScreen createState() => _ChoiceScreen();
}
class _ChoiceScreen extends State<ChoiceScreen>
{
  bool isChoosen = false;

  @override

  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Wybierz części stałe mszy",
          textAlign: TextAlign.left,
          style: TextStyle(fontSize: 18),
        ),
        actions:[
          Visibility(
              visible: isChoosen,
              child: IconButton(
                  onPressed: (){
                    Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => PresentationScreen()
                          )
                      );
                    },
                  icon: Icon(Icons.arrow_forward_rounded)
              )
          ),

        ],
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ElevatedButton(
                style: buttonPrimary,
                onPressed: (){
                  currentMass[0] = songList[0];
                  currentMass[1] = songList[1];
                  currentMass[2] = songList[2];
                  isMassDone = true;
                  i=0;
                  FinalList = [
                    Plan[0], currentMass[0],Plan[1],currentMass[1],currentMass[2],Plan[2], Plan[3],Plan[4],
                  ];
                  Navigator.pushReplacement(
                      context,
                      MaterialPageRoute(
                          builder: (BuildContext context) => MenuScreen()
                      ));



                },
                child: Text("Msza Pokoju")
            ),
            SizedBox(
              height: 100,
            ),
            ElevatedButton(
                style: buttonPrimary,
                onPressed: (){
                  currentMass[0] = songList[6];
                  currentMass[1] = songList[7];
                  currentMass[2] = songList[8];
                  isMassDone = true;
                  i=0;
                  FinalList = [
                    Plan[0], currentMass[0],Plan[1],currentMass[1],currentMass[2],Plan[2], Plan[3],Plan[4],
                  ];
                  Navigator.pushReplacement(
                      context,
                      MaterialPageRoute(
                          builder: (BuildContext context) => MenuScreen()
                      ));


                },
                child: Text("Msza św. Macieja")
            ),
            SizedBox(
              height: 100,
            ),
            ElevatedButton(
                style: buttonPrimary,
                onPressed: (){
                  currentMass[0] = songList[3];
                  currentMass[1] = songList[4];
                  currentMass[2] = songList[5];
                  isMassDone = true;
                  i=0;
                  FinalList = [
                    Plan[0], currentMass[0],Plan[1],currentMass[1],currentMass[2],Plan[2], Plan[3],Plan[4],
                  ];
                  Navigator.pushReplacement(
                      context,
                      MaterialPageRoute(
                          builder: (BuildContext context) => MenuScreen()
                      ));


                },
                child: Text("Msza św. Faustyny")
            ),
            ],
        )
        ,
      ),

    );
  }
}
