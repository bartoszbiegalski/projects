import 'package:flutter/material.dart';
import 'package:sampleproject/Model/piesni.dart';
import 'package:sampleproject/Screens/OptionsScreen.dart';
import 'package:sampleproject/Screens/PresentationScreen.dart';
import 'package:sampleproject/Screens/ProgramCreator.dart';
import 'package:sampleproject/Screens/button.dart';
import 'package:sampleproject/Screens/SongMenu.dart';
import 'package:sampleproject/Screens/SongSearch.dart';
import 'package:flutter/services.dart';
import 'package:sampleproject/main.dart';

int j=0;



bool isMassDone = false;
class MenuScreen extends StatelessWidget {

  const MenuScreen({super.key});
  @override
  Widget build(BuildContext context) {

    return Scaffold(
        appBar: AppBar(
          title: const Text(
              'Śpiewnik Maciejówki ',
            style: TextStyle(fontSize: 30),
            textAlign: TextAlign.center,
          ),
          automaticallyImplyLeading: false,
        ),

        body: Center(

        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ElevatedButton(
              style: buttonPrimary,

              onPressed: ()
              {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => ProgramCreator()
                    )
                );
              },
              child: Text("Stwórz program pieśni na mszę"),

            ),
            Container(
              height: 30,
            ),
            ElevatedButton(
              style:  isMassDone== true? buttonPrimary : buttonDisabled,
              onPressed: ()
              {
                if(isMassDone == true)
                  {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => PresentationScreen()
                        )
                    );
                  }
                else
                  {
                    ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(
                            content: Text("Aby wyświetlić program musisz go najpierw stworzyć.")
                        )
                    );
                  }
              },
              child: Text("Wyświetl program mszy"),
            ),
            Container(
              height: 30,
            ),
           ElevatedButton(
              style: buttonPrimary,
               onPressed: ()
                {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => SongSearchScreenState()
                      )
                  );
                },
                child: Text("Lista pieśni"),

           ),
            Container(
              height: 30,
            ),

            ElevatedButton(
              style: buttonPrimary,
              onPressed: ()
              {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => OptionsScreen()
                    )
                );
              },
              child: Text("Opcje"),
            ),






          ]
        )




        )


    );

                  /*onTap: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => SongListScreen()
                        )
                    );

                  },
                ),
              );
          */


  }
}