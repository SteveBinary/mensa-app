# Mensa API

website: https://stwulm.my-mensa.de/mensatogo.php?mensa=15

## POST order menu

POST: https://stwulm.my-mensa.de/setDataMensaTogo.php?order=add&language=de

- as URL-encoded form data
- Retrofit: https://stackoverflow.com/questions/37814857/retrofit-2-with-only-form-data

```
client%5Beinrichtung%5D=Heidenheim%2C+DHBW%2C+Cafeteria+Marienstra%C3%9Fe&client%5Beinrichtung_val%5D=15&client%5Bvorname%5D=Max&client%5Bname%5D=Mustermann&client%5Bemail%5D=mustermannm.tin20%40student.dhbw-heidenheim.de&client%5Bnv2%5D=on&client%5Bsave_allowed%5D=on&client%5Bdeliver_time_val%5D=12%3A00&client%5Bdate_iso%5D=2023-02-28&client%5Bdate_hr%5D=Di%2C+28.02.2023&basket_positions%5B2561285%5D=1&basket_html=%3Ctbody%3E%3Ctr%3E%3Cth%3EAnzahl%3C%2Fth%3E+%3Cth%3EArtikel%3C%2Fth%3E+%3Cth+class%3D%22zahl%22%3ESt%C3%BCckpreis%3C%2Fth%3E%3C%2Ftr%3E+%3Ctr%3E%3Ctd%3E1x%3C%2Ftd%3E+%3Ctd+aid_check%3D%222561285%22%3EPuten%C2%ADge%C2%ADsch%C2%ADnet%C2%ADzeltes+Stro%C2%ADganoff+%3Csup%3E(2%2C3%2C9%2C24%2C27)%3C%2Fsup%3E%3C%2Ftd%3E+%3Ctd+class%3D%22preis%22%3EStudierende%3A+4%2C45%26nbsp%3B%E2%82%AC%3Cbr%3EBedienstete%3A+0%2C00%26nbsp%3B%E2%82%AC%3Cbr%3EG%C3%A4ste%3A+8%2C50%26nbsp%3B%E2%82%AC%3C%2Ftd%3E%3C%2Ftr%3E+%3Ctr+class%3D%22trenner%22%3E%3Ctd%3E%3C%2Ftd%3E+%3Ctd%3E%3C%2Ftd%3E+%3Ctd%3E%3C%2Ftd%3E%3C%2Ftr%3E%3C%2Ftbody%3E&basket_full%5B2561285%5D%5Bid%5D=2561285&basket_full%5B2561285%5D%5Bcategory%5D=Fleisch+und+Fisch&basket_full%5B2561285%5D%5Btitle%5D=Puten%26shy%3Bge%26shy%3Bsch%26shy%3Bnet%26shy%3Bzeltes+Stro%26shy%3Bganoff+%3Csup%3E(2%2C3%2C9%2C24%2C27)%3C%2Fsup%3E+Spiral%26shy%3Bnu%26shy%3Bdeln+%3Csup%3E(34W)%3C%2Fsup%3E+(2%2C3%2C9%2C24%2C27%2C34W)&basket_full%5B2561285%5D%5Bpreis1%5D=4%2C45&basket_full%5B2561285%5D%5Bpreis2%5D=0%2C00&basket_full%5B2561285%5D%5Bpreis3%5D=8%2C50&basket_full%5B2561285%5D%5Banzahl%5D=1
```

decoded version:

```
client[einrichtung]=Heidenheim,+DHBW,+Cafeteria+Marienstraße
&client[einrichtung_val]=15
&client[vorname]=Max
&client[name]=Mustermann
&client[email]=mustermannm.tin19@student.dhbw-heidenheim.de
&client[nv2]=on
&client[save_allowed]=on
&client[deliver_time_val]=12:00
&client[date_iso]=2023-02-28
&client[date_hr]=Di,+28.02.2023
&basket_positions[2561285]=1
&basket_html=<tbody><tr><th>Anzahl</th>+<th>Artikel</th>+<th+class="zahl">Stückpreis</th></tr>+<tr><td>1x</td>+<td+aid_check="2561285">Puten­ge­sch­net­zeltes+Stro­ganoff+<sup>(2,3,9,24,27)</sup></td>+<td+class="preis">Studierende:+4,45&nbsp;€<br>Bedienstete:+0,00&nbsp;€<br>Gäste:+8,50&nbsp;€</td></tr>+<tr+class="trenner"><td></td>+<td></td>+<td></td></tr></tbody>
&basket_full[2561285][id]=2561285
&basket_full[2561285][category]=Fleisch+und+Fisch
&basket_full[2561285][title]=Puten&shy;ge&shy;sch&shy;net&shy;zeltes+Stro&shy;ganoff+<sup>(2,3,9,24,27)</sup>+Spiral&shy;nu&shy;deln+<sup>(34W)</sup>+(2,3,9,24,27,34W)
&basket_full[2561285][preis1]=4,45
&basket_full[2561285][preis2]=0,00
&basket_full[2561285][preis3]=8,50
&basket_full[2561285][anzahl]=1
```

## GET all available menus

GET: https://stwulm.my-mensa.de/getdata.php?mensa_id=15&json=1&hyp=1&now=1677229910272&mode=togo&lang=de

```json
{
  "mensaname": "Heidenheim, DHBW, Cafeteria Marienstraße",
  "result": [
    {
      "tag": {
        "timestamp": "1677193200",
        "tag_formatiert": "Fr <small>(24.02.)<\/small>",
        "tag_formatiert2": "Fr, 24.02.2023",
        "tag_formatiert_rel": "heute",
        "jahrestag": "54",
        "wochentag": "Freitag",
        "wochentag_short": "Fr",
        "datum": "24.02.",
        "datum2": "24.02.",
        "datum_iso": "2023-02-24",
        "wota_index": "5",
        "kw": "08"
      },
      "essen": [
        {
          "category": "Fleisch und Fisch",
          "title": "Cordon bleu vom Schwein, gefüllt mit Berg&shy;käse und Schinken <sup>(2,3,8,14,24)<\/sup>",
          "description": "Braten&shy;soße <sup>(26,34W,34G)<\/sup>, Patatas Bravas",
          "kennzeichnungen": "2,3,8,14,24,26,34G,34W,S",
          "beilagen": "",
          "preis1": "4,40",
          "preis2": "0,00",
          "preis3": "8,40",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "94",
            "artikelId": "2561226"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Cordon bleu vom Schwein, gefüllt mit Bergkäse und Schinken ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Cordon bleu vom Schwein, gefüllt mit Bergkäse und Schinken <sup>(2,3,8,14,24)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "Bratensoße , Patatas Bravas",
          "md5Source": "cordon bleu vom schwein, gefüllt mit bergkäse und schinken <sup>(2,3,8,14,24)<\/sup> bratensoße <sup>(26,34w,34g)<\/sup>, patatas bravas",
          "md5": "0171c632c9552034270eddff0298210b",
          "kat_id": "94",
          "loc_id": "15",
          "preis1_eur": "4,40&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 4,40&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "8,40&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 8,40&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561226",
          "kennzRest": "(2,3,8,14,24,26,34G,34W)",
          "icons2": {
            "S": {
              "high": ".\/images\/\/ulm\/S_hi.gif",
              "low": ".\/images\/\/ulm\/S.gif",
              "text": "Schwein"
            }
          },
          "preis_formated": "4,40&nbsp;&euro; \/ 8,40&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 4,40&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 8,40&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 4,40&nbsp;&euro;<br \/>Gäste: 8,40&nbsp;&euro;"
        },
        {
          "category": "Sattmacher",
          "title": "Spätzle <sup>(14)<\/sup>",
          "description": "mit Braten&shy;soße <sup>(26,34W,34G)<\/sup>",
          "kennzeichnungen": "14,26,34G,34W,veg",
          "beilagen": "",
          "preis1": "2,00",
          "preis2": "0,00",
          "preis3": "3,80",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "105",
            "artikelId": "2561228"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Spätzle ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Spätzle <sup>(14)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "mit Bratensoße ",
          "md5Source": "spätzle <sup>(14)<\/sup> mit bratensoße <sup>(26,34w,34g)<\/sup>",
          "md5": "7573dbf1a3eb44fa6fba5e07ca5e3797",
          "kat_id": "105",
          "loc_id": "15",
          "preis1_eur": "2,00&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 2,00&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "3,80&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 3,80&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561228",
          "kennzRest": "(14,26,34G,34W)",
          "icons2": {
            "veg": {
              "high": ".\/images\/\/ulm\/veg_hi.gif",
              "low": ".\/images\/\/ulm\/veg.gif",
              "text": "vegetarisch"
            }
          },
          "preis_formated": "2,00&nbsp;&euro; \/ 3,80&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 2,00&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 3,80&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 2,00&nbsp;&euro;<br \/>Gäste: 3,80&nbsp;&euro;"
        },
        {
          "category": "Prima Klima",
          "title": "Pasta Asciutta mit Makka&shy;roni <sup>(11,14,26,30)<\/sup>",
          "description": "Reibe&shy;käse <sup>(24)<\/sup>",
          "kennzeichnungen": "11,14,24,26,30,veg",
          "beilagen": "",
          "preis1": "3,70",
          "preis2": "0,00",
          "preis3": "7,05",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "95",
            "artikelId": "2561227"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Pasta Asciutta mit Makkaroni ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Pasta Asciutta mit Makkaroni <sup>(11,14,26,30)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "Reibekäse ",
          "md5Source": "pasta asciutta mit makkaroni <sup>(11,14,26,30)<\/sup> reibekäse <sup>(24)<\/sup>",
          "md5": "b59bba1e478f1879bc65240d898e5dcc",
          "kat_id": "95",
          "loc_id": "15",
          "preis1_eur": "3,70&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 3,70&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "7,05&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 7,05&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561227",
          "kennzRest": "(11,14,24,26,30)",
          "icons2": {
            "veg": {
              "high": ".\/images\/\/ulm\/veg_hi.gif",
              "low": ".\/images\/\/ulm\/veg.gif",
              "text": "vegetarisch"
            }
          },
          "preis_formated": "3,70&nbsp;&euro; \/ 7,05&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 3,70&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 7,05&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 3,70&nbsp;&euro;<br \/>Gäste: 7,05&nbsp;&euro;"
        }
      ]
    },
    {
      "tag": {
        "timestamp": "1677193200",
        "tag_formatiert": "Mo <small>(27.02.)<\/small>",
        "tag_formatiert2": "Mo, 27.02.2023",
        "tag_formatiert_rel": "in 3 Tagen",
        "jahrestag": "57",
        "wochentag": "Montag",
        "wochentag_short": "Mo",
        "datum": "27.02.",
        "datum2": "27.02.",
        "datum_iso": "2023-02-27",
        "wota_index": "1",
        "kw": "09"
      },
      "essen": [
        {
          "category": "Fleisch und Fisch",
          "title": "Hähn&shy;chen-Gyros",
          "description": "Zwie&shy;bel&shy;schei&shy;ben, Zaziki <sup>(24)<\/sup>, Reis",
          "kennzeichnungen": "24,G",
          "beilagen": "",
          "preis1": "4,10",
          "preis2": "0,00",
          "preis3": "7,80",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "94",
            "artikelId": "2561287"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Hähnchen-Gyros ",
          "icons": [],
          "icons_kuerzel": [
            "G"
          ],
          "title2": "Hähnchen-Gyros ",
          "alreadyExtracted_description": true,
          "description_clean": "Zwiebelscheiben, Zaziki , Reis",
          "md5Source": "hähnchen-gyros zwiebelscheiben, zaziki <sup>(24)<\/sup>, reis",
          "md5": "c01d93258af1a49f8444fbeab850f12b",
          "kat_id": "94",
          "loc_id": "15",
          "preis1_eur": "4,10&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 4,10&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "7,80&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 7,80&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561287",
          "kennzRest": "(24)",
          "icons2": {
            "G": {
              "high": ".\/images\/\/ulm\/G_hi.gif",
              "low": ".\/images\/\/ulm\/G.gif",
              "text": "Huhn"
            }
          },
          "preis_formated": "4,10&nbsp;&euro; \/ 7,80&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 4,10&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 7,80&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 4,10&nbsp;&euro;<br \/>Gäste: 7,80&nbsp;&euro;"
        },
        {
          "category": "Sattmacher",
          "title": "Kräu&shy;ter-Kar&shy;tof&shy;fel&shy;püree <sup>(3,8,24)<\/sup>",
          "description": "Rahm&shy;soße <sup>(24,26,34W,34G)<\/sup>",
          "kennzeichnungen": "3,8,24,26,34G,34W,veg",
          "beilagen": "",
          "preis1": "1,80",
          "preis2": "0,00",
          "preis3": "3,45",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "105",
            "artikelId": "2561289"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Kräuter-Kartoffelpüree ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Kräuter-Kartoffelpüree <sup>(3,8,24)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "Rahmsoße ",
          "md5Source": "kräuter-kartoffelpüree <sup>(3,8,24)<\/sup> rahmsoße <sup>(24,26,34w,34g)<\/sup>",
          "md5": "7d7e45157655bf02980568119daf96c6",
          "kat_id": "105",
          "loc_id": "15",
          "preis1_eur": "1,80&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 1,80&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "3,45&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 3,45&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561289",
          "kennzRest": "(3,8,24,26,34G,34W)",
          "icons2": {
            "veg": {
              "high": ".\/images\/\/ulm\/veg_hi.gif",
              "low": ".\/images\/\/ulm\/veg.gif",
              "text": "vegetarisch"
            }
          },
          "preis_formated": "1,80&nbsp;&euro; \/ 3,45&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 1,80&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 3,45&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 1,80&nbsp;&euro;<br \/>Gäste: 3,45&nbsp;&euro;"
        },
        {
          "category": "Prima Klima",
          "title": "Rote Bete Gnocchi mit gegrilltem Wurzel&shy;ge&shy;müse <sup>(23,26)<\/sup>",
          "description": "",
          "kennzeichnungen": "23,26",
          "beilagen": "",
          "preis1": "3,95",
          "preis2": "0,00",
          "preis3": "7,55",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "95",
            "artikelId": "2561288"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Rote Bete Gnocchi mit gegrilltem Wurzelgemüse ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Rote Bete Gnocchi mit gegrilltem Wurzelgemüse <sup>(23,26)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "",
          "md5Source": "rote bete gnocchi mit gegrilltem wurzelgemüse <sup>(23,26)<\/sup>",
          "md5": "54873c078de47c490ee6804cb76680c5",
          "kat_id": "95",
          "loc_id": "15",
          "preis1_eur": "3,95&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 3,95&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "7,55&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 7,55&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561288",
          "kennzRest": "(23,26)",
          "icons2": [],
          "preis_formated": "3,95&nbsp;&euro; \/ 7,55&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 3,95&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 7,55&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 3,95&nbsp;&euro;<br \/>Gäste: 7,55&nbsp;&euro;"
        }
      ]
    },
    {
      "tag": {
        "timestamp": "1677538800",
        "tag_formatiert": "Di <small>(28.02.)<\/small>",
        "tag_formatiert2": "Di, 28.02.2023",
        "tag_formatiert_rel": "in 4 Tagen",
        "jahrestag": "58",
        "wochentag": "Dienstag",
        "wochentag_short": "Di",
        "datum": "28.02.",
        "datum2": "28.02.",
        "datum_iso": "2023-02-28",
        "wota_index": "2",
        "kw": "09"
      },
      "essen": [
        {
          "category": "Fleisch und Fisch",
          "title": "Puten&shy;ge&shy;sch&shy;net&shy;zeltes Stro&shy;ganoff <sup>(2,3,9,24,27)<\/sup>",
          "description": "Spiral&shy;nu&shy;deln <sup>(34W)<\/sup>",
          "kennzeichnungen": "2,3,9,24,27,34W,G",
          "beilagen": "",
          "preis1": "4,45",
          "preis2": "0,00",
          "preis3": "8,50",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "94",
            "artikelId": "2561285"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Putengeschnetzeltes Stroganoff ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Putengeschnetzeltes Stroganoff <sup>(2,3,9,24,27)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "Spiralnudeln ",
          "md5Source": "putengeschnetzeltes stroganoff <sup>(2,3,9,24,27)<\/sup> spiralnudeln <sup>(34w)<\/sup>",
          "md5": "deb4b31fa71fde0874a5860a86546f87",
          "kat_id": "94",
          "loc_id": "15",
          "preis1_eur": "4,45&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 4,45&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "8,50&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 8,50&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561285",
          "kennzRest": "(2,3,9,24,27,34W)",
          "icons2": {
            "G": {
              "high": ".\/images\/\/ulm\/G_hi.gif",
              "low": ".\/images\/\/ulm\/G.gif",
              "text": "Huhn"
            }
          },
          "preis_formated": "4,45&nbsp;&euro; \/ 8,50&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 4,45&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 8,50&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 4,45&nbsp;&euro;<br \/>Gäste: 8,50&nbsp;&euro;"
        },
        {
          "category": "Sattmacher",
          "title": "Grießbrei <sup>(3,24)<\/sup>",
          "description": "Apfelmus <sup>(3)<\/sup>",
          "kennzeichnungen": "3,24,veg",
          "beilagen": "",
          "preis1": "1,80",
          "preis2": "0,00",
          "preis3": "3,45",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "105",
            "artikelId": "2561286"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Grießbrei ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Grießbrei <sup>(3,24)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "Apfelmus ",
          "md5Source": "grießbrei <sup>(3,24)<\/sup> apfelmus <sup>(3)<\/sup>",
          "md5": "a8e82d026ca7270d90f2782a0fa6bfa8",
          "kat_id": "105",
          "loc_id": "15",
          "preis1_eur": "1,80&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 1,80&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "3,45&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 3,45&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561286",
          "kennzRest": "(3,24)",
          "icons2": {
            "veg": {
              "high": ".\/images\/\/ulm\/veg_hi.gif",
              "low": ".\/images\/\/ulm\/veg.gif",
              "text": "vegetarisch"
            }
          },
          "preis_formated": "1,80&nbsp;&euro; \/ 3,45&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 1,80&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 3,45&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 1,80&nbsp;&euro;<br \/>Gäste: 3,45&nbsp;&euro;"
        },
        {
          "category": "Topf und Pfanne",
          "title": "Nasi Goreng mit Karot&shy;ten, Erbsen und Bambus <sup>(23,27)<\/sup>",
          "description": "",
          "kennzeichnungen": "23,27",
          "beilagen": "",
          "preis1": "3,30",
          "preis2": "0,00",
          "preis3": "6,30",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "93",
            "artikelId": "2561284"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Nasi Goreng mit Karotten, Erbsen und Bambus ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Nasi Goreng mit Karotten, Erbsen und Bambus <sup>(23,27)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "",
          "md5Source": "nasi goreng mit karotten, erbsen und bambus <sup>(23,27)<\/sup>",
          "md5": "c1a653303226df32181007c379a62219",
          "kat_id": "93",
          "loc_id": "15",
          "preis1_eur": "3,30&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 3,30&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "6,30&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 6,30&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561284",
          "kennzRest": "(23,27)",
          "icons2": [],
          "preis_formated": "3,30&nbsp;&euro; \/ 6,30&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 3,30&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 6,30&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 3,30&nbsp;&euro;<br \/>Gäste: 6,30&nbsp;&euro;"
        }
      ]
    },
    {
      "tag": {
        "timestamp": "1677625200",
        "tag_formatiert": "Mi <small>(01.03.)<\/small>",
        "tag_formatiert2": "Mi, 01.03.2023",
        "tag_formatiert_rel": "in 5 Tagen",
        "jahrestag": "59",
        "wochentag": "Mittwoch",
        "wochentag_short": "Mi",
        "datum": "01.03.",
        "datum2": "01.03.",
        "datum_iso": "2023-03-01",
        "wota_index": "3",
        "kw": "09"
      },
      "essen": [
        {
          "category": "Sattmacher",
          "title": "Makka&shy;roni <sup>(34W)<\/sup>",
          "description": "mit Toma&shy;ten&shy;sugo",
          "kennzeichnungen": "34W",
          "beilagen": "",
          "preis1": "2,00",
          "preis2": "0,00",
          "preis3": "3,80",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "105",
            "artikelId": "2561283"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Makkaroni ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Makkaroni <sup>(34W)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "mit Tomatensugo",
          "md5Source": "makkaroni <sup>(34w)<\/sup> mit tomatensugo",
          "md5": "b7c40b554140fdaf91456762dab078ae",
          "kat_id": "105",
          "loc_id": "15",
          "preis1_eur": "2,00&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 2,00&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "3,80&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 3,80&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561283",
          "kennzRest": "(34W)",
          "icons2": [],
          "preis_formated": "2,00&nbsp;&euro; \/ 3,80&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 2,00&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 3,80&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 2,00&nbsp;&euro;<br \/>Gäste: 3,80&nbsp;&euro;"
        },
        {
          "category": "Prima Klima",
          "title": "Gerös&shy;tete Gemü&shy;se&shy;maul&shy;ta&shy;schen mit Ei <sup>(14,24)<\/sup>",
          "description": "Zwie&shy;bel&shy;soße <sup>(26,34W,34G)<\/sup>",
          "kennzeichnungen": "14,24,26,34G,34W,veg",
          "beilagen": "",
          "preis1": "4,00",
          "preis2": "0,00",
          "preis3": "7,60",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "95",
            "artikelId": "2561282"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Geröstete Gemüsemaultaschen mit Ei ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Geröstete Gemüsemaultaschen mit Ei <sup>(14,24)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "Zwiebelsoße ",
          "md5Source": "geröstete gemüsemaultaschen mit ei <sup>(14,24)<\/sup> zwiebelsoße <sup>(26,34w,34g)<\/sup>",
          "md5": "281245d60ed41f9d814c91c69c4aedc2",
          "kat_id": "95",
          "loc_id": "15",
          "preis1_eur": "4,00&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 4,00&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "7,60&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 7,60&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561282",
          "kennzRest": "(14,24,26,34G,34W)",
          "icons2": {
            "veg": {
              "high": ".\/images\/\/ulm\/veg_hi.gif",
              "low": ".\/images\/\/ulm\/veg.gif",
              "text": "vegetarisch"
            }
          },
          "preis_formated": "4,00&nbsp;&euro; \/ 7,60&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 4,00&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 7,60&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 4,00&nbsp;&euro;<br \/>Gäste: 7,60&nbsp;&euro;"
        },
        {
          "category": "Topf und Pfanne",
          "title": "Grie&shy;chi&shy;sches Ofen&shy;ge&shy;müse mit Kartof&shy;feln, Paprika, Zucchini und Auberginen",
          "description": "",
          "kennzeichnungen": "",
          "beilagen": "",
          "preis1": "3,40",
          "preis2": "0,00",
          "preis3": "6,50",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "93",
            "artikelId": "2561281"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Griechisches Ofengemüse mit Kartoffeln, Paprika, Zucchini und Auberginen",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Griechisches Ofengemüse mit Kartoffeln, Paprika, Zucchini und Auberginen",
          "alreadyExtracted_description": true,
          "description_clean": "",
          "md5Source": "griechisches ofengemüse mit kartoffeln, paprika, zucchini und auberginen",
          "md5": "e859ba00859c4dd2ba34d8d457723713",
          "kat_id": "93",
          "loc_id": "15",
          "preis1_eur": "3,40&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 3,40&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "6,50&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 6,50&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561281",
          "kennzRest": "",
          "icons2": [],
          "preis_formated": "3,40&nbsp;&euro; \/ 6,50&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 3,40&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 6,50&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 3,40&nbsp;&euro;<br \/>Gäste: 6,50&nbsp;&euro;"
        }
      ]
    },
    {
      "tag": {
        "timestamp": "1677711600",
        "tag_formatiert": "Do <small>(02.03.)<\/small>",
        "tag_formatiert2": "Do, 02.03.2023",
        "tag_formatiert_rel": "in 6 Tagen",
        "jahrestag": "60",
        "wochentag": "Donnerstag",
        "wochentag_short": "Do",
        "datum": "02.03.",
        "datum2": "02.03.",
        "datum_iso": "2023-03-02",
        "wota_index": "4",
        "kw": "09"
      },
      "essen": [
        {
          "category": "Fleisch und Fisch",
          "title": "Alaska Seelachs im Back&shy;teig <sup>(35)<\/sup>",
          "description": "Remou&shy;la&shy;den&shy;soße, <sup>(2,9,14,24,30,35)<\/sup>, Peter&shy;si&shy;li&shy;en&shy;kar&shy;tof&shy;feln",
          "kennzeichnungen": "2,9,14,24,30,35,F",
          "beilagen": "",
          "preis1": "4,40",
          "preis2": "0,00",
          "preis3": "8,40",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "94",
            "artikelId": "2561278"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Alaska Seelachs im Backteig ",
          "icons": [],
          "icons_kuerzel": [
            "F"
          ],
          "title2": "Alaska Seelachs im Backteig <sup>(35)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "Remouladensoße, , Petersilienkartoffeln",
          "md5Source": "alaska seelachs im backteig <sup>(35)<\/sup> remouladensoße, <sup>(2,9,14,24,30,35)<\/sup>, petersilienkartoffeln",
          "md5": "358bdd36b5f6b9c24fcd6d811b78fda5",
          "kat_id": "94",
          "loc_id": "15",
          "preis1_eur": "4,40&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 4,40&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "8,40&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 8,40&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561278",
          "kennzRest": "(2,9,14,24,30,35)",
          "icons2": {
            "F": {
              "high": ".\/images\/\/ulm\/F_hi.gif",
              "low": ".\/images\/\/ulm\/F.gif",
              "text": "Fisch"
            }
          },
          "preis_formated": "4,40&nbsp;&euro; \/ 8,40&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 4,40&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 8,40&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 4,40&nbsp;&euro;<br \/>Gäste: 8,40&nbsp;&euro;"
        },
        {
          "category": "Sattmacher",
          "title": "Risi Bisi",
          "description": "mit Frisch&shy;käse-Kräu&shy;ter&shy;soße <sup>(24)<\/sup>",
          "kennzeichnungen": "24,veg",
          "beilagen": "",
          "preis1": "2,00",
          "preis2": "0,00",
          "preis3": "3,80",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "105",
            "artikelId": "2561279"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Risi Bisi",
          "icons": [],
          "icons_kuerzel": [
            "F"
          ],
          "title2": "Risi Bisi",
          "alreadyExtracted_description": true,
          "description_clean": "mit Frischkäse-Kräutersoße ",
          "md5Source": "risi bisi mit frischkäse-kräutersoße <sup>(24)<\/sup>",
          "md5": "3ff941b9b2b88d979b9d8eb2f2d8edb4",
          "kat_id": "105",
          "loc_id": "15",
          "preis1_eur": "2,00&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 2,00&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "3,80&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 3,80&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561279",
          "kennzRest": "(24)",
          "icons2": {
            "veg": {
              "high": ".\/images\/\/ulm\/veg_hi.gif",
              "low": ".\/images\/\/ulm\/veg.gif",
              "text": "vegetarisch"
            }
          },
          "preis_formated": "2,00&nbsp;&euro; \/ 3,80&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 2,00&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 3,80&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 2,00&nbsp;&euro;<br \/>Gäste: 3,80&nbsp;&euro;"
        },
        {
          "category": "Topf und Pfanne",
          "title": "Erbsen&shy;eintopf mit Kartof&shy;feln und Gemüse <sup>(26)<\/sup>",
          "description": "1 Semmel <sup>(34W,34G)<\/sup>",
          "kennzeichnungen": "26,34G,34W",
          "beilagen": "",
          "preis1": "3,30",
          "preis2": "0,00",
          "preis3": "6,30",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "93",
            "artikelId": "2561277"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Erbseneintopf mit Kartoffeln und Gemüse ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Erbseneintopf mit Kartoffeln und Gemüse <sup>(26)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "1 Semmel ",
          "md5Source": "erbseneintopf mit kartoffeln und gemüse <sup>(26)<\/sup> 1 semmel <sup>(34w,34g)<\/sup>",
          "md5": "598fb7a152d228b5fd75fb86e6c2ec62",
          "kat_id": "93",
          "loc_id": "15",
          "preis1_eur": "3,30&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 3,30&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "6,30&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 6,30&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561277",
          "kennzRest": "(26,34G,34W)",
          "icons2": [],
          "preis_formated": "3,30&nbsp;&euro; \/ 6,30&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 3,30&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 6,30&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 3,30&nbsp;&euro;<br \/>Gäste: 6,30&nbsp;&euro;"
        },
        {
          "category": "Extra",
          "title": "1 Wien&shy;erle <sup>(2,3,8)<\/sup>",
          "description": "",
          "kennzeichnungen": "2,3,8,L,R,S",
          "beilagen": "",
          "preis1": "1,00",
          "preis2": "0,00",
          "preis3": "1,90",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "106",
            "artikelId": "2561280"
          },
          "alreadyExtracted_title": true,
          "title_clean": "1 Wienerle ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "1 Wienerle <sup>(2,3,8)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "",
          "md5Source": "1 wienerle <sup>(2,3,8)<\/sup>",
          "md5": "dab82544dab78d4324efb35ab7dccd19",
          "kat_id": "106",
          "loc_id": "15",
          "preis1_eur": "1,00&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 1,00&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "1,90&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 1,90&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561280",
          "kennzRest": "(2,3,8)",
          "icons2": {
            "L": {
              "high": ".\/images\/\/ulm\/L_hi.gif",
              "low": ".\/images\/\/ulm\/L.gif",
              "text": "Lamm"
            },
            "R": {
              "high": ".\/images\/\/ulm\/R_hi.gif",
              "low": ".\/images\/\/ulm\/R.gif",
              "text": "Rind"
            },
            "S": {
              "high": ".\/images\/\/ulm\/S_hi.gif",
              "low": ".\/images\/\/ulm\/S.gif",
              "text": "Schwein"
            }
          },
          "preis_formated": "1,00&nbsp;&euro; \/ 1,90&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 1,00&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 1,90&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 1,00&nbsp;&euro;<br \/>Gäste: 1,90&nbsp;&euro;"
        }
      ]
    },
    {
      "tag": {
        "timestamp": "1677798000",
        "tag_formatiert": "Fr <small>(03.03.)<\/small>",
        "tag_formatiert2": "Fr, 03.03.2023",
        "tag_formatiert_rel": "in 7 Tagen",
        "jahrestag": "61",
        "wochentag": "Freitag",
        "wochentag_short": "Fr",
        "datum": "03.03.",
        "datum2": "03.03.",
        "datum_iso": "2023-03-03",
        "wota_index": "5",
        "kw": "09"
      },
      "essen": [
        {
          "category": "Fleisch und Fisch",
          "title": "Hähn&shy;chen&shy;schnitzel im Cornflakes-Mantel <sup>(34W,34G)<\/sup>",
          "description": "Geflügel&shy;soße Brat&shy;kartoffeln",
          "kennzeichnungen": "34G,34W,G",
          "beilagen": "",
          "preis1": "4,40",
          "preis2": "0,00",
          "preis3": "8,40",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "94",
            "artikelId": "2561275"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Hähnchenschnitzel im Cornflakes-Mantel ",
          "icons": [],
          "icons_kuerzel": [
            "G"
          ],
          "title2": "Hähnchenschnitzel im Cornflakes-Mantel <sup>(34W,34G)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "Geflügelsoße  Bratkartoffeln",
          "md5Source": "hähnchenschnitzel im cornflakes-mantel <sup>(34w,34g)<\/sup> geflügelsoße  bratkartoffeln",
          "md5": "b157824ffcd4fedc2452a6c8eb7e4263",
          "kat_id": "94",
          "loc_id": "15",
          "preis1_eur": "4,40&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 4,40&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "8,40&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 8,40&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561275",
          "kennzRest": "(34G,34W)",
          "icons2": {
            "G": {
              "high": ".\/images\/\/ulm\/G_hi.gif",
              "low": ".\/images\/\/ulm\/G.gif",
              "text": "Huhn"
            }
          },
          "preis_formated": "4,40&nbsp;&euro; \/ 8,40&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 4,40&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 8,40&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 4,40&nbsp;&euro;<br \/>Gäste: 8,40&nbsp;&euro;"
        },
        {
          "category": "Sattmacher",
          "title": "Spätzle <sup>(14)<\/sup>",
          "description": "mit Braten&shy;soße <sup>(26,34W,34G)<\/sup>",
          "kennzeichnungen": "14,26,34G,34W,veg",
          "beilagen": "",
          "preis1": "2,00",
          "preis2": "0,00",
          "preis3": "3,80",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "105",
            "artikelId": "2561276"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Spätzle ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Spätzle <sup>(14)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "mit Bratensoße ",
          "md5Source": "spätzle <sup>(14)<\/sup> mit bratensoße <sup>(26,34w,34g)<\/sup>",
          "md5": "7573dbf1a3eb44fa6fba5e07ca5e3797",
          "kat_id": "105",
          "loc_id": "15",
          "preis1_eur": "2,00&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 2,00&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "3,80&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 3,80&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561276",
          "kennzRest": "(14,26,34G,34W)",
          "icons2": {
            "veg": {
              "high": ".\/images\/\/ulm\/veg_hi.gif",
              "low": ".\/images\/\/ulm\/veg.gif",
              "text": "vegetarisch"
            }
          },
          "preis_formated": "2,00&nbsp;&euro; \/ 3,80&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 2,00&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 3,80&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 2,00&nbsp;&euro;<br \/>Gäste: 3,80&nbsp;&euro;"
        },
        {
          "category": "Topf und Pfanne",
          "title": "Land&shy;hausp&shy;fanne mit Spira&shy;len, Gemüse und Rahm&shy;soße <sup>(23,26)<\/sup>",
          "description": "",
          "kennzeichnungen": "23,26",
          "beilagen": "",
          "preis1": "3,40",
          "preis2": "0,00",
          "preis3": "6,50",
          "einheit": "",
          "foto": "",
          "attributes": {
            "produktionId": "93",
            "artikelId": "2561274"
          },
          "alreadyExtracted_title": true,
          "title_clean": "Landhauspfanne mit Spiralen, Gemüse und Rahmsoße ",
          "icons": [],
          "icons_kuerzel": [],
          "title2": "Landhauspfanne mit Spiralen, Gemüse und Rahmsoße <sup>(23,26)<\/sup>",
          "alreadyExtracted_description": true,
          "description_clean": "",
          "md5Source": "landhauspfanne mit spiralen, gemüse und rahmsoße <sup>(23,26)<\/sup>",
          "md5": "5bd993fba58e1036c8f85cd8a8a4d6d0",
          "kat_id": "93",
          "loc_id": "15",
          "preis1_eur": "3,40&nbsp;&euro;",
          "preis1_eur_label": "Studierende: 3,40&nbsp;&euro;",
          "preis2_eur": "0,00&nbsp;&euro;",
          "preis2_eur_label": "Bedienstete: 0,00&nbsp;&euro;",
          "preis3_eur": "6,50&nbsp;&euro;",
          "preis3_eur_label": "Gäste: 6,50&nbsp;&euro;",
          "preis4_eur": "-",
          "preis4_eur_label": "Schüler: -",
          "preis5_eur": "-",
          "preis5_eur_label": "Lieferpreis: -",
          "a_id": "2561274",
          "kennzRest": "(23,26)",
          "icons2": [],
          "preis_formated": "3,40&nbsp;&euro; \/ 6,50&nbsp;&euro;",
          "preis_formated_LS": "Lieferpreis: -",
          "preis_formated_Togo": "Studierende: 3,40&nbsp;&euro;<br \/>Bedienstete: 0,00&nbsp;&euro;<br \/>Gäste: 6,50&nbsp;&euro;",
          "preis_formated_Togo_small": "Stud.: 3,40&nbsp;&euro;<br \/>Gäste: 6,50&nbsp;&euro;"
        }
      ]
    }
  ]
}
```
