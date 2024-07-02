import 'package:flutter/material.dart';
import 'package:sampleproject/Model/piesni.dart';
import 'package:sampleproject/Screens/SongDetailScreen.dart';




class SongListScreen extends StatelessWidget {

  SongListScreen({Key? key}) : super(key: key);
  List <Song> currSongs = songList;



  @override
  Widget build(BuildContext context) {
  return Scaffold(
    appBar: AppBar(
      title: Text('Lista pieśni'),

    ),

    body: Padding(
        padding: const EdgeInsets.all(10.0),
        child: Column(
          children: [

             TextField(

              decoration: InputDecoration(
                  labelText: "Wyszukaj pieśń", suffixIcon: Icon(Icons.search)
              ),
               //onChanged: searchSong,
            ),
            const SizedBox(
              height: 20,
            ),
            Expanded(
              child:ListView.builder(
                  itemCount: songList.length,

                  itemBuilder: (context, index) {
                    Song song = songList[index];
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



    )
  );

}
  void searchSong(String query)
  {
    final suggestions = songList.where((song) {
      final songTitle = song.title.toLowerCase();
      final input = query.toLowerCase();
      return songTitle.contains(input);
    }
    ).toList();


  }


}