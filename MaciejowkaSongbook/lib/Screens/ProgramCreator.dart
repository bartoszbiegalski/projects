import 'package:flutter/material.dart';
import 'package:sampleproject/Model/piesni.dart';
import 'package:photo_view/photo_view.dart';
import 'package:sampleproject/Screens/SongDetailScreen.dart';
import 'package:sampleproject/main.dart';
import 'package:sampleproject/Screens/PresentationScreen.dart';
import 'package:sampleproject/Screens/ChoiceScreen.dart';
import 'package:sampleproject/Screens/SongMenu.dart';

int i =0;

class ProgramCreator extends StatefulWidget{
  @override
  _ProgramCreator createState() => _ProgramCreator();
}
class _ProgramCreator extends State<ProgramCreator>
{
  int SelectedIndex = -1;
  bool isMassDone = false;
  bool isChoosen = false;
  List<Song> currSongList = [];
  @override
  void initState() {
    for(var i in songList)
      {
        if(i.type == "piesn")
          {
              currSongList.add(i);
          }
      }
    super.initState();
  }

  void _runFilter(String enteredKeyword) {
    List<Song> results = [];
    if (enteredKeyword.isEmpty) {
      // if the search field is empty or only contains white-space, we'll display all users
      results = songList;
    } else {
      results = songList
          .where((user) =>
          user.title.toLowerCase().contains(enteredKeyword.toLowerCase()))
          .toList();

      // we use the toLowerCase() method to make it case-insensitive
    }
    setState(() {
      currSongList = results;
    });
  }

  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Wybierz pieśń na " + Kolejnosc[i],
            textAlign: TextAlign.left,
          style: TextStyle(fontSize: 18),

        ),
        leading: BackButton(
          onPressed: (){
            if(i>0)
            {
              i--;
              {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => ProgramCreator()
                    )

                );
              }
            }
            else
              {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => MenuScreen()
                    )

                );
              }
          },
        ),
        actions:[
          Visibility(
            visible: isChoosen,
              child: IconButton(
                  onPressed: (){
                    if(i != 4)
                    {
                      i++;
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => ProgramCreator()
                          )

                      );

                    }
                    else
                    {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => ChoiceScreen()
                          )
                      );
                    }
                  },
                  icon: Icon(Icons.arrow_forward_rounded)
              )
          ),

        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: Column(
          children: [
            TextField(
              onChanged: (value) => _runFilter(value),
              decoration: const InputDecoration(
                  labelText: 'Search', suffixIcon: Icon(Icons.search)),
            ),
            const SizedBox(
              height: 20,
            ),
            Expanded(
                child:ListView.builder(
                    itemCount: currSongList.length,

                    itemBuilder: (context, index) {
                      Song song = currSongList[index];
                      if(song.type == "piesn")
                        {
                          return Card(
                            child: ListTile(

                              tileColor: SelectedIndex == index? Colors.red : null,
                              title: Text(song.title),
                              textColor: SelectedIndex == index? Colors.white : null,
                              trailing: Icon(Icons.arrow_forward_rounded),
                              onTap: () {

                                setState(() {
                                  isChoosen = true;
                                  SelectedIndex = index;

                                  Plan[i] = currSongList[index];
                                  print(Plan[i].title);
                                  print(i);
                                });
                              },
                            ),
                          );
                        }
                        else return Column(

                      );

                    })

            )
          ],
        )
        ,
      ),

    );
  }
}
