import 'package:flutter/material.dart';
import 'package:sampleproject/Model/piesni.dart';
import 'package:photo_view/photo_view.dart';
import 'package:sampleproject/Screens/SongDetailScreen.dart';


class SongSearchScreenState extends StatefulWidget{
  @override
  _SongSearchScreenState createState() => _SongSearchScreenState();
}
class _SongSearchScreenState extends State<SongSearchScreenState>
{
  List<Song> currSongList = [];
  @override
  void initState() {
    currSongList = songList;
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
        title: Text("Lista Pieśni"),
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
                      return Card(
                        child: ListTile(
                          title: Text(song.title),

                          trailing: Icon(Icons.arrow_forward_rounded),
                          onTap: () {
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
        )
       ,
      ),

    );
  }
}
