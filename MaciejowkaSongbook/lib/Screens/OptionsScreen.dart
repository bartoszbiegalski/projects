import 'package:flutter/material.dart';
import 'package:sampleproject/Screens/SongMenu.dart';
import 'package:sampleproject/Screens/button.dart';
import 'package:flutter/services.dart';



bool FullscreenOn = true;
bool DarkmodeOn = false;

class OptionsScreen extends StatefulWidget{
  @override
  _OptionsScreen createState() => _OptionsScreen();
}
class _OptionsScreen extends State<OptionsScreen>
{


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text(
            'Opcje',
            style: TextStyle(fontSize: 30),
          ),

        ),
        body: Center(

            child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Directionality(
                    textDirection: TextDirection.rtl,
                    child: CheckboxListTile(
                      value: FullscreenOn,
                      onChanged: (bool? value)
                      {
                        setState(() {
                          FullscreenOn = value!;
                        });
                        if(value == true)
                          {
                            SystemChrome.setEnabledSystemUIMode
                              (
                                SystemUiMode.manual,
                                overlays: []
                            );
                          }
                        else
                          {
                            SystemChrome.setEnabledSystemUIMode
                              (
                                SystemUiMode.manual,
                                overlays: [SystemUiOverlay.bottom, SystemUiOverlay.top]
                            );
                          }
                      },
                      title: Text("Tryb pełnoekranowy")
                    ),
                  ),
                  Container(
                    height: 30,
                  ),
                  Container(
                    height: 30,
                  ),

                ]
            )
        )
    );
  }
}