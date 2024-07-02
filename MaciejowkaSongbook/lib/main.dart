import 'package:flutter/material.dart';
import 'Screens/SongMenu.dart';
import 'Screens/SongSearch.dart';
import 'package:photo_view/photo_view.dart';
import 'Model/piesni.dart';
import 'package:flutter/services.dart';
import 'package:flutter/services.dart';




List <String> Kolejnosc = [
  "wejście", "ofiarowanie", "komunię", "uwielbienie", "rozesłanie"
];
List <String> Kolejnosc1 = [
  "Wejście: ", "",  "Ofiarowanie: ", "", "",  "Komunia: ", "Uwielbienie: ", "Rozesłanie: "
];
List <String> Msze = [
  "Msza św. Macieja", "Msza Pokoju", "Msza św. Faustyny",
];
List <Song> Plan = [
  /// 1. Wejście
  /// 2. Panie, zmiłuj się
  /// 3. Ofiarowanie
  /// 4. Święty
  /// 5. Baranku Boży
  /// 6. Komunia
  /// 7. Uwielbienie
  /// 8. Rozesłanie
  Song(
    title:  "Msza Pokoju - Panie, zmiłuj się",
    site:  13,
    imageUrl: "lib/Screens/pictures/NŚM v.2.0-013.png",
    type: "pieśń",
    howMuchSites: 1,
    currSite: 13,
  ),
  Song(
    title:  "Msza Pokoju - Panie, zmiłuj się",
    site:  13,
    imageUrl: "lib/Screens/pictures/NŚM v.2.0-013.png",
    type: "msza",
    howMuchSites: 1,
    currSite: 13,
  ),
  Song(
    title:  "Msza Pokoju - Panie, zmiłuj się",
    site:  13,
    imageUrl: "lib/Screens/pictures/NŚM v.2.0-013.png",
    type: "pieśń",
    howMuchSites: 1,
    currSite: 13,
  ),
  Song(
    title:  "Msza Pokoju - Panie, zmiłuj się",
    site:  13,
    imageUrl: "lib/Screens/pictures/NŚM v.2.0-013.png",
    type: "msza",
    howMuchSites: 1,
    currSite: 13,
  ),
  Song(
    title:  "Msza Pokoju - Panie, zmiłuj się",
    site:  13,
    imageUrl: "lib/Screens/pictures/NŚM v.2.0-013.png",
    type: "msza",
    howMuchSites: 1,
    currSite: 13,
  ),
  Song(
    title:  "Msza Pokoju - Panie, zmiłuj się",
    site:  13,
    imageUrl: "lib/Screens/pictures/NŚM v.2.0-013.png",
    type: "msza",
    howMuchSites: 1,
    currSite: 13,
  ),
  Song(
    title:  "Msza Pokoju - Panie, zmiłuj się",
    site:  13,
    imageUrl: "lib/Screens/pictures/NŚM v.2.0-013.png",
    type: "msza",
    howMuchSites: 1,
    currSite: 13,
  ),
  Song(
    title:  "Msza Pokoju - Panie, zmiłuj się",
    site:  13,
    imageUrl: "lib/Screens/pictures/NŚM v.2.0-013.png",
    type: "msza",
    howMuchSites: 1,
    currSite: 13,
  ),
];

void main() {
  //WidgetsFlutterBinding.ensureInitialized();s\
  SetFullscreen()
  {
    SystemChrome.setEnabledSystemUIMode
      (
        SystemUiMode.immersive,
        overlays: []
    );
  }

  SetFullscreen();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {

   const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    SystemChrome.setEnabledSystemUIMode(SystemUiMode.manual,overlays: [SystemUiOverlay.top]);
    return MaterialApp(

      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.red,
        visualDensity: VisualDensity.adaptivePlatformDensity,

      ),
      home: MenuScreen(),
    );
  }


}
