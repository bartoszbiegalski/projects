import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:sampleproject/Model/piesni.dart';
import 'package:photo_view/photo_view.dart';
import 'package:sampleproject/Screens/button.dart';




String nextSite(Song song) {
  String s;
  if (song.howMuchSites == 1) {
    return song.imageUrl;
  }
  else {
    int x = int.parse(song.imageUrl.substring(31, 34)) + 1;
    if (x >= 100) {
      s = song.imageUrl.substring(0, 31) +
          x.toString() + ".png";
      print(s);
    }
    else {
      s = (song.imageUrl.substring(0, 31) + "0" +
          x.toString() + ".png");}


      return s;

    }
}

String prevSite(Song song) {
  String s;
  if (song.howMuchSites == 1) {
    return song.imageUrl;
  }
  else {
    int x = int.parse(song.imageUrl.substring(31, 34))  - 1;
    if (x >= 100) {
      s = song.imageUrl.substring(0, 31) +
          x.toString() + ".png";
    }
    else {
      s = (song.imageUrl.substring(0, 31) + "0" +
          x.toString() + ".png");}


    return s;
  }
}
bool isVisible(Song song)
{
  if(song.howMuchSites == 1)
  {
    return false;
  }
  else
  {
    return true;
  }
}
IconData arrow(Song song)
{
  if(song.site == song.currSite ) return Icons.arrow_forward_rounded;
  else return Icons.arrow_back_rounded;
}




class SongDetailsScreen extends StatelessWidget {
  final Song song;

  SongDetailsScreen(this.song);

  @override
  Widget build(BuildContext context) {
    print(nextSite(song));
    return Scaffold(

      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Text(song.title),

      ),
    body: OrientationBuilder(
      builder: (context, orientation)
        {
          return Column(
            children: [
              Container(
                height: orientation == Orientation.portrait ? MediaQuery.of(context).size.height * 0.76 : MediaQuery.of(context).size.height * 0.55,
                child: PhotoView(

                  backgroundDecoration: BoxDecoration(color: Colors.white),
                  imageProvider: AssetImage(song.imageUrl),

                  minScale:  PhotoViewComputedScale.contained * 1,
                  maxScale:  PhotoViewComputedScale.covered * 2,


                ),

              ),

              Visibility(
                visible: isVisible(song),
                child:Padding(

                  padding: EdgeInsets.all(8.0),
                  child: ElevatedButton(
                    //style: buttonPrimary,
                    onPressed: ()
                    {
                      if(song.howMuchSites > 1)
                      {
                        if(song.currSite == song.site)
                        {
                          song.imageUrl = nextSite(song);
                          song.currSite = song.currSite + 1;
                          IconData x = Icons.arrow_forward_rounded;
                          Navigator.pushReplacement(
                              context,
                              MaterialPageRoute(
                                  builder: (BuildContext context) => SongDetailsScreen(song)
                              ));
                        }
                        else
                        {
                          song.imageUrl = prevSite(song);
                          song.currSite = song.currSite - 1;
                          Navigator.pushReplacement(
                              context,
                              MaterialPageRoute(
                                  builder: (BuildContext context) => SongDetailsScreen(song)
                              ));
                        }

                      }



                    },
                    child: Icon(arrow(song)),
                  ),
                ),

              )

            ],
          );
        },

      ),
    );




  }

}