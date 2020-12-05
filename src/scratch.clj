(ns scratch)

;; NOTES

(comment
  "I think the key is that the defday functions are *just* thin callers of other functions.
  They delegate all other work to helpers. Should look like:"
  (defn- other-helper [])
  (defn- solve-puzzle [board is-valid?])
  ;; TODO how to handle tests
  (defday 1
          "12531245111111111111114534514513451345134513451345134513" ;; input
          (fn parse-board [input])  ;; or a reference, parse-board, or anon #(->> % s/split-lines)
          (fn single-slope [board] (solve-puzzle board #(= 1 %)))
          (fn multi-trees [board] (solve-puzzle board #(#{1 2} %)))))

;; Goal is just to stop having to repeatedly name things
;; How can I better re-use the boring string parsing bits?
;; defday should have a pre-parser form?

;; (defday input pre algo-a algo-b & tests)

;; What does every day have?
;;  - Input
;;  - Algo a & solution a
;;  - Algo b & solution b
;;  - Test input & test solution
;;  - Maybe: pre-parsing step
;;  - Maybe: soln b is just an improvement / superset of functionality of soln a

;; Could have a variant that takes the URL and does magic with that

;; Then, I could have utilities with days. E.g. based on the 'today' value,
;; I could even just modify the behavior of defday to be a no-op for non-today,
;; and could also have var that controls whetehr it prins the solutions.

;(defday 1
;  "Input"
;  (fn []) ; preparse
;  (fn []) ;; A
;  (fn []) ;; B, optional
;  & [[expected-a expected-b test-input]])
;
;(defday 2
;  "6-10 s: snkscgszxsssscss\n6-7 b: bbbbbxkb\n2-4 n: nnnjn\n1-2 j: jjjj\n5-9 z: jgzzzqhbj\n4-11 m: mfmmmpmjmkdr\n12-15 t: twqrxttwttthtkxbz\n8-9 z: ftzjzzzzr\n17-18 h: cpkhssvpphzvprfnft\n7-8 b: bjbbbbbb\n4-5 p: pppppppppgppps\n16-18 r: rrrrrrrrrrrrrrrrrr\n9-16 v: vvvrpvbvvvvvvvvvwvvh\n11-15 d: ddddddddddddddjd\n9-14 g: ggbggghggggggggw\n1-5 d: ddddbd\n1-4 x: xxxwxxx\n1-2 l: bdjjddlqg\n1-4 b: lbbxb\n15-16 f: ffffffffffffffmz\n10-16 m: mmlmmmvmmmbhmmmq\n4-15 v: vlfvvqphhjfvlgt\n5-12 m: mmmmjmwmmmmcmmm\n7-8 q: qqqqqqxkq\n4-9 h: hhzhhhhhhhhhsh\n3-7 t: thltdtjtstzrtwtt\n6-7 k: kkkkkkk\n1-5 q: jqwqd\n4-13 x: xxxxxxxxxxxxxxxxxx\n1-4 l: llfl\n6-12 n: nnffnfnnmnffnx\n4-6 m: xmvxnmpmm\n5-7 m: mmmmbmmmmmmmmm\n9-17 f: ffffflffbfffffcffff\n4-10 k: kkklkkkkkhktkkbkzq\n8-15 z: kdxxzzlhpzgbzjzz\n2-5 q: qrqqbqqqqqqqkqq\n3-5 t: zrttht\n9-12 t: ttxgntjmvntctpfrt\n2-3 k: kkkk\n8-10 j: jjjjjjjjjj\n2-9 k: vkwkhcqnk\n9-10 t: ttttttthtt\n4-6 b: bbbbbbbb\n9-12 n: xnvnnvldhthlsn\n2-4 w: wwwwwwj\n6-10 t: tttttwttttvtt\n3-10 j: jqjjjxjdjnjjjj\n15-18 q: kqlncdqwclqpjzrbnq\n7-8 p: gpwbjppp\n3-13 m: mmmmlsmfvmhmmmm\n7-10 s: wdshsrsgsl\n8-16 f: fffffffxfffffffcf\n16-18 s: ssskswhsvslwsssrsq\n12-14 j: gjjjjkjgjkjhjvj\n13-14 t: gtttvftwtgvhlt\n6-7 v: vvvvvgbv\n2-8 l: ssldslmvl\n3-9 l: bflzllqkqlkll\n8-9 n: rrnnnnnnsn\n7-14 p: bqsrxgplkpdvbpkn\n2-5 z: wzbclnsxt\n2-4 k: knkxk\n6-8 d: drddjddjdd\n15-16 p: ztgptpfpcwppqrzppps\n1-3 k: nmsqksv\n7-10 n: pwhbcwnlznfnvrlnds\n13-15 t: tttttttttttttttttt\n10-12 b: bbbbbbdbbgfxbbbf\n3-5 w: rwwwj\n18-19 x: xxxxmxxxxwpxxxfxgln\n1-4 g: lfsjtgggg\n13-15 x: mxzgtxhrxjhxtnf\n13-14 d: ddddddddddddlx\n3-5 r: rrrtrrtmlr\n1-7 t: thttttttttt\n2-5 g: sngggj\n8-9 n: tnnnnnnnn\n6-10 w: wkwwjfwltk\n12-18 n: nnnnnnnnnnnnnnsnnh\n14-18 m: mmmmmmmmmmmmmmmmmmmm\n5-15 v: fvvfrcqkvkggnpl\n13-19 f: ffffffftfffdnffffpsf\n13-15 k: kgkkkmkkkgftklb\n4-6 h: hhhchvh\n7-14 l: qmbdhdjbrglxql\n13-17 w: kwwwwwwwwwwwdpwww\n1-5 q: pqqqdqqqq\n8-9 p: vhwphvpfp\n2-4 h: hljmh\n1-2 c: tmcc\n3-5 f: dpnvwffhlp\n3-5 m: mmmmmm\n10-12 c: ngjnczmcscxc\n2-4 x: mxxj\n4-6 m: xbmkmm\n1-6 l: kllllsblqhngl\n11-18 x: xprkjcrxkgxxgwtbmx\n4-6 k: vwxshkkkkbtfbhl\n11-15 z: dzzkzjjzzzzzzzlmzbn\n5-6 f: ffffnfffflfjff\n3-6 d: ddddddbd\n6-9 r: wrrrvrrrr\n8-9 n: nnnnjnnnt\n2-7 b: bxptdkctbrxfllpvj\n3-4 x: hxxv\n5-14 n: nrnnnlsnznnzrqnjxnp\n1-3 w: wwww\n9-12 x: xxmxxxxxxxxxtxx\n10-14 j: jjnjjjjlvjkcsjjdllvj\n4-6 w: wgwbwwww\n10-12 t: ttqttttttttl\n10-11 t: ttwtttttttv\n9-15 t: ttttttttftttttttt\n2-3 p: pfgp\n2-4 z: brrksnvqjzwqjvjs\n8-9 n: knnnftzjbqj\n6-7 w: swjwwdwwwwwwwww\n1-2 j: xjlrkjjztrjfpss\n13-14 l: llllllllllllvlllllr\n7-12 l: lllllllllllblllll\n7-8 j: gqdcmjqh\n3-9 g: gchkbvgvgw\n15-17 b: bbbbcbbbgbbbbbmbg\n1-7 h: qlmhhcdhhhhhhhhhh\n2-15 c: ccccccccccccccscccc\n1-2 q: xvch\n3-9 w: wwwwwwxwww\n2-4 x: jskx\n3-7 t: btwsnvts\n9-11 g: gkhgwctggbm\n2-7 g: xvgwwltdvnzscbtqwb\n1-11 k: kkqkpkkdkkkkkkkhkk\n4-12 h: nbqlhjbqhxtnxlzlr\n3-4 d: dwdd\n8-9 k: kkkzbckklqqkhfkqkk\n7-8 c: pcxmqdwc\n2-4 v: qbvwvvnvvp\n3-9 c: pfqcblwxcrmx\n1-9 t: fwgtcrftktt\n7-8 p: pppppppp\n6-13 w: dnwfhwkjffpdwgzdf\n10-14 k: kkkkkkhkkwkkkkkk\n2-4 s: zscssddtpmqblmd\n11-13 b: dbvzwthlcmbkb\n1-4 k: kqlxwvbkckwzmqxvtcc\n2-10 s: sxssssgsssxsssnss\n3-9 w: nxrmwlpgw\n10-13 w: wwsgwwwwwwwkw\n9-13 v: vhvvwjvcvbvvg\n5-7 n: fbvnjpt\n3-4 x: xxxx\n9-10 l: rlllmlllll\n7-9 d: ddddddddhd\n4-8 z: zzzhzzzf\n11-12 m: mmmmmmmmmmkf\n4-8 m: bmfzppcqttct\n2-4 m: mfmcmmmcx\n2-9 p: rppzgvshn\n7-8 r: rrrrrrrrrrvr\n6-7 p: hkltppp\n7-10 m: mmmmmfmmtmmmpbmm\n6-14 p: pppppppppppppxp\n4-6 p: wbspppnlmc\n2-4 n: xnfnl\n2-5 q: nqkcqmvwp\n2-3 d: ddkbkdjd\n5-9 b: qxjbblbrb\n14-16 b: bbbbbbbbbbbbbqbbb\n5-6 p: dppppp\n5-8 j: kxztnjjn\n14-18 k: krkkkkckkkkmktkkkkd\n7-8 n: nnngxnfpnn\n16-17 c: ccmccccccccccccfcccc\n1-3 g: wggg\n1-2 c: cqcckc\n2-8 g: gjwdkbds\n10-17 s: hswqssmttskspdlkkss\n14-16 b: bbbbbbbbbbbbpbbrbbbb\n12-16 r: srrmcbrrcrzrdwzng\n2-3 h: fcws\n9-13 p: gkgpqpghpjbpz\n9-10 m: mmmmmmmmmmm\n3-4 b: brbb\n2-3 p: gncvqdhp\n2-7 g: gdggggggggggggg\n2-7 j: jjjjjjrjqjjj\n3-5 v: ltptjlntf\n1-7 w: gwwwwwfww\n7-10 z: zzzzzzmzzzz\n10-12 x: xxhxxxxxxgxsxxxx\n11-12 b: bbbbbbbbbbbbd\n16-17 v: vvvvvvvvvvvvvvjht\n1-4 x: bxxx\n16-19 g: gggggggwggggggjgggg\n5-10 q: qjpqqvlnkqxmlv\n4-6 p: qrpffplvpp\n7-9 r: rrrrrrgrb\n2-3 g: bgjg\n10-12 t: ttjttttnttdtt\n2-4 g: dgng\n11-12 r: rrmrrrlrtrrj\n1-5 v: qvvvvvvvvvvvvvv\n3-18 p: ftnfnpmjprmrzmhbnxj\n10-12 w: dqnsjjxsqrwq\n2-4 z: lzgkzsb\n8-9 n: dwclxdtnndtpcgqmx\n10-11 w: zjwdsphbbwlhp\n2-9 d: zzprkgzczbs\n14-19 c: gpjfrvsrcnbxbclctxc\n1-9 f: fmhfvfffrfws\n12-14 d: ddrddbddddddddhmdg\n14-15 s: sslsssmsnssdxsj\n2-10 v: ljppzjfvfnfp\n1-2 z: qbwbzknrzzs\n18-19 l: llllllllfllllllllll\n2-3 p: ppgp\n13-15 k: knkkkkkckkkkhkxk\n3-6 v: vbjvmv\n4-8 h: hmhcxfhg\n12-14 v: vvngcvvvvkvgvs\n2-3 j: xdjjrk\n5-13 s: sssssrqsssssq\n1-7 s: sssssssss\n6-14 b: ptlvmbbrbxnvqbrmp\n16-18 w: wwwswwnwwmwwwwwwwf\n1-4 k: kkknkkkkk\n1-4 g: ggrbk\n9-11 r: rrrhrrrrrrrrr\n11-13 x: xxxxxxxxxxxxnx\n3-8 x: nsxvlvdfbkpxsgscn\n12-15 h: hghhchvjhhhhlvhhhhh\n11-12 n: nnnnnnnnnnjqn\n10-13 r: jrzfzrzrqblmks\n7-8 w: wwwwwwwh\n3-4 k: kkbft\n1-9 c: cccccjccccccn\n2-7 r: rrvnrxrvrzsrbrs\n3-10 l: llgdldmlplqlhdlll\n4-8 m: mmzmbmgm\n5-6 q: sfxqnftnbnqwq\n5-6 v: vvvvgvvqv\n9-14 h: dhmwrhzqqvhhhfhfhhht\n4-7 p: jwwppvpklc\n9-13 z: zzzzzzzzzpzzzx\n13-14 l: fllklltlllllcphllll\n12-15 m: htmbmqgljcmvmclgnm\n9-10 b: bbbbbbbnbb\n6-12 x: nxjbxxhxxxxgdxx\n6-7 t: tttbwlk\n8-11 q: fqqjtlswqgkqdqc\n16-20 t: xplwqxbwtsfptbvtvcxt\n3-4 k: kklkkkkkkxkkkk\n3-9 m: kmmsvmpmxmgmb\n6-12 j: jjtjjjjjjjjpjjwj\n3-4 m: kkmbmzkx\n7-8 f: gmfxlgfjtfst\n1-3 w: wwwn\n1-7 k: knkktkkn\n13-16 v: vvvvvvvvvvvvvvvmvv\n10-11 h: rhvdhnfhvtchlfhh\n10-12 x: xgxwbqxkxxcwsfd\n10-14 m: mmmrdxpcmcmmmkmmmmm\n2-5 x: vvxpx\n8-9 h: hhhhhhhmfh\n16-19 j: jmwjjjjjjjjjjjjjjjz\n2-3 r: sqrnr\n14-19 p: ppppppppppppppppppk\n2-5 w: wwwww\n15-16 q: dppxtnhmxrhmncrqq\n4-5 v: drhpp\n1-4 p: pkhn\n3-6 q: gwmrfwh\n6-10 v: dlvvfhvvkb\n1-8 q: qqqqqqqqqdqqqqqqqq\n17-18 n: nnnnnnnnnxnnznnpnng\n3-7 m: gmxzffwmbdm\n12-14 m: dmzmcmmhjmqltm\n3-16 w: drcbwtvqgbppbwzvm\n8-10 d: ddbddddzdddddkpd\n8-9 m: mmmmmmmmmmmm\n1-3 l: glll\n7-11 x: fxrxxdxxxqxnxx\n6-7 d: ddddxgdd\n7-8 g: hgggbggg\n2-3 w: pwwtww\n7-10 h: lhwbhjhzhx\n3-14 b: bbbrvrbsfbnxrgqbbq\n4-7 l: jzqkxlnpxlglfsll\n6-7 b: hbpbbbb\n2-4 w: pskwwxzpjvmwcnfr\n13-15 m: mmmmmmmmxmpmxmmm\n4-9 k: krxkzqzkkhrpqth\n4-7 f: xfffkvfqzwhcfwkhq\n4-10 q: qqqqqcqbqqq\n14-15 s: lgszsdststlpgjbs\n7-12 s: sspbsssskfns\n7-8 d: cdddqpnd\n13-15 p: ppppppppppppxppp\n10-11 r: rrrfxrrrrrrrrr\n10-12 z: zzzzzzzzzvzvzz\n3-4 l: lllwll\n7-9 p: ppppkpppqpppppsvx\n3-4 k: kkkrk\n4-5 v: zxmmvmhvr\n14-15 r: rrrrrrrrrrrrrfjr\n6-10 b: vwbgbbbbdbqsbb\n5-7 j: jsjjjkv\n3-4 m: tzmhr\n11-13 r: rrrrrrrrrrqrhrr\n1-4 s: psqmcssnk\n5-6 z: zdkzzzz\n3-5 p: cnpmbtknqdppmcjpzvcn\n13-14 g: ggggkggggmtggd\n14-17 c: nxmzcczccnvdcxpcmb\n6-10 w: wlwwbdwwwwwwww\n9-17 l: llllllllllllllllll\n2-12 j: jpjdjjjzmjxj\n1-3 n: szjntnnl\n7-8 l: dpxlmhlbts\n5-16 s: sssssssssssssssrs\n1-6 m: pmqvlc\n7-15 f: ffffffffqfkfffdxff\n10-11 w: wwwwwwwwwwswwww\n7-12 p: tkppvfpwksrp\n4-7 f: fdjdgdvsksbfbnjkspc\n4-11 b: bjmvfrmlmlbndl\n1-3 r: rrrnxhgbwr\n5-10 h: xhhmhhhlhbh\n10-11 h: hghhhhhhdlhhh\n5-6 g: gptglg\n9-11 t: wtgtxtttqtmttt\n5-11 f: jxqffhfsfmz\n4-7 f: frjffbf\n1-15 m: mmmmmmmmxmmmmmmmm\n1-12 d: drwlbpdbzdgdjpnzmj\n9-14 n: nfnmktjnncnnnln\n1-2 h: pshhjhhhhf\n2-4 t: tgttttttfb\n2-4 w: dwpwhnxbf\n7-11 b: vbcbzhbwhpb\n4-5 k: hknck\n5-12 m: mmmmtdmmmmmmmm\n5-7 w: qwflwxq\n2-5 g: jdnxdlclplvb\n5-7 t: ttrtttttkr\n8-18 l: lwscjwlmdlzlllnsllpw\n2-8 c: cccxtccchbrkr\n1-3 q: qqtqqqqq\n3-13 d: dddddddbddddddd\n1-7 r: jkpzrpfnrgpkc\n9-10 t: ttttttttttt\n11-12 w: wjwplwwwwwmw\n9-12 r: rpsvrhrbnrwqch\n6-11 p: zpzbpkpmtnptsnpbswc\n4-6 t: jhzstt\n3-4 t: ddpkcgpzhd\n4-6 s: cvzshjfrsslxnslqddwt\n1-3 c: cgsczhl\n1-4 d: lddqdqd\n6-7 v: vvvvvvb\n5-8 w: wwwwwwwwww\n5-11 p: ddswpbpppsqpp\n5-6 x: dfzxxxhhqjvj\n3-7 m: mmmmmmmmmm\n4-8 g: ggggggggg\n5-6 j: jjcjjn\n5-6 n: nndtns\n4-5 t: ltxkdpst\n2-8 b: ldwkbzbxgpbbbb\n11-12 w: wwqwdqwwwqhvwww\n15-17 k: klkcxqbwrktxcmqlnb\n5-6 k: tkqhkkkkrkjfd\n12-13 x: nxxxrjxxxdjlxxbt\n9-12 m: mmzmmmrmmfmgmmmmb\n2-5 v: tkbhvlvp\n12-14 v: dvbvvvvhrcvvvxxvvvvv\n14-16 x: xxxxxxxxxxxxxrxxxx\n6-7 v: xvswnvvm\n6-9 c: vcpcksxwbdlc\n5-11 s: sssssjssssrsdjsssq\n6-16 j: jjjjjmjjjjjjjjjnj\n8-12 g: ggggwggggggnggg\n4-5 c: ccczk\n14-15 l: lllllllllllllkm\n10-11 r: rrrrrrrrrrrrr\n3-5 n: dwnvnlj\n2-4 x: xdxxxx\n5-6 j: jjjjjjj\n3-9 k: kkkkkhlrkktt\n3-7 t: stqtgfdprtqjsgznrtjh\n11-16 n: hsnnnnnnnnfnvnpqnnn\n13-16 v: kdvbvxvvtvhvvvvq\n1-3 v: vwpvkvdpxgc\n14-17 v: vvvvvvvvvvvvvnvvhv\n14-15 m: mmmmmmmmmmmmmtm\n6-10 m: mmmmmhmmmm\n5-8 t: tttjrttdsttfttwstf\n6-7 c: ccqcvcc\n2-5 v: vvvvvv\n8-16 c: cccccccccwcpcccmcccc\n4-5 t: ztvttktttttt\n10-11 n: rnmnnnnnnnnnnnnpt\n5-6 g: lgsggggg\n15-18 s: lcsmgkjqzdpcgvsrng\n2-6 p: wwpppp\n9-10 v: vvvvsvvvmvv\n6-11 c: qrmdjxzsmxcmcccgrr\n17-18 b: bbbbbbbbbbbbbbbbbbbb\n6-10 t: thtxgtpxwt\n2-8 f: xkgbrpqfsrhhbnfpdg\n3-8 c: gqkkvgkcqfwdc\n5-6 c: jjcsjf\n8-16 q: fbwdmlwljqqcrqsq\n3-10 m: mjmmsmmmmmmcmm\n5-15 x: xmrvcsjwxxdpsrxzcrzj\n3-7 x: rxngzxxg\n17-19 z: mmvfgzpmbvzsrmkgmmmb\n3-4 z: zdzz\n4-5 v: vzcvb\n3-11 z: tzllpgzzmwxznh\n8-11 x: ptgvnbxzsxwdb\n2-3 r: rrrrr\n6-10 b: bbmbbbbbbb\n6-11 q: wvmqrzrllhxfzmpkp\n13-17 v: vvrgvvsvvcqkvvvvbvvw\n16-18 p: ppppppppnppgppppclp\n13-15 g: gglggggggngggggggggg\n15-17 b: bbbbbbbbbbbbbbbbb\n3-6 t: tpwzktlzkdt\n2-3 m: lmvtnfjzmm\n1-7 j: jzjjfjx\n15-17 r: rtfdnhrrhrrdcswrl\n1-3 d: gdfdmddddwdddlsd\n3-4 r: qrtr\n10-11 m: mmmmmmmmmkc\n2-5 n: gntnnnncc\n9-14 b: bbbbbbbbbbbbbb\n10-13 f: fxfffznffffzwffflz\n5-12 j: vjjrjjjjngjjjjm\n6-8 k: kkkrkklkkkkkkk\n5-7 r: rrhzsxrjjw\n17-18 g: gggggggggggggggggf\n2-3 h: ghhvrh\n1-3 j: hjjxjjjj\n3-5 q: gbqnqkprckxqglkhw\n17-18 n: ngnnnnnqnnnnnnnnnnnn\n2-3 m: xlhmmq\n6-7 p: kvvphgj\n9-12 k: kpkzkkzkkkkkbwk\n9-10 k: kkrkkkkkmt\n5-10 t: tttttttttttttttt\n10-11 l: ztdvlllzfltlwlglkhcj\n7-12 t: bttttqtqttttttttg\n3-4 z: xzvb\n5-10 h: lhcdhkhhhhb\n1-6 z: jqzzqzzzzzzzzzzzzz\n6-7 x: qxxxxtxz\n13-14 g: gggngggggggrgsgg\n1-2 t: tttt\n11-15 w: hpwhwmwlkbwtwmwlj\n8-10 r: rnrhrrmprnrrr\n5-12 z: rshzhwgzhfjb\n15-16 s: sssqssssssssmsss\n9-12 z: zdzzzzzzkzmz\n10-12 c: mfgpskncfcfctjmt\n5-10 p: sppwnpdpptppwdppppp\n3-14 q: mnzfgfmvmsdlqg\n1-13 s: sssssssfssssbsss\n6-8 d: ddddddddd\n2-5 j: vsbbjh\n14-16 m: nmklmsrpjxwpdbmj\n3-8 v: rfvvvjqv\n18-20 p: pppppppppqhpflppbppp\n7-11 x: xxxxxxwxxxx\n3-4 l: llmvlll\n8-9 w: grmvwtwbz\n2-11 m: mmmmmmqmmmsmmmmmmmmm\n6-13 h: hhhhhrshshhhlhhhh\n3-12 t: tnttblttttcvrtttx\n5-18 f: cfffcffvgffffrmlfx\n1-3 n: rnjnnnnnnnnnnnnnnn\n1-2 w: smmdq\n1-5 v: vvvvvv\n5-15 s: tssssscssffhsswssss\n3-11 b: bbbbbbbbsbkbbbr\n13-15 j: xjjpjjcjjjjjjcj\n5-10 g: bvjvgpzgdgkmmzwnwrxz\n2-7 q: qqqqqrq\n10-12 d: fddddbdddxdqpd\n5-7 c: hsccjccrlm\n11-15 r: nrrrlrrvrdlrprw\n13-14 r: drrtbrrrrrrrrcrrr\n8-10 c: ccccxcchfjcccccxpc\n8-9 s: sssssssss\n1-10 r: rvlgkrrrrm\n8-13 w: jwbwcwmwwvwwwwww\n7-12 k: fxkkcnkkbvkxbkphx\n7-8 l: llllmlvll\n11-15 q: qdjpqwgfqjdqnnq\n8-9 m: mmmmmmmvmmm\n6-7 k: kkkksmkb\n4-6 h: hhhlhhhhfhhhhhh\n2-5 k: xzjlwb\n3-12 j: sjwqrjzjgqrj\n5-18 p: ppppppppppppppppphpp\n1-3 g: gggrggggg\n14-15 n: nnnnnnnnnnxnnhnnn\n19-20 d: jkpzsxwsddzccjdkcptj\n2-4 v: wpdz\n1-8 w: dwjtngwwwrwhp\n13-15 t: tttttctttttxvfrt\n6-11 h: ksvshqhhdth\n8-9 k: khfkdkppl\n11-12 d: whlmcdmfgfddd\n11-12 p: htvxpkfngthz\n6-9 v: vqvmvvbbv\n5-8 m: lghmmmrmmw\n14-16 q: dtmqqrqqqqqgpgqtqr\n2-11 f: bfjskxzlgvfml\n10-19 p: kpppppppphppppppppp\n5-7 j: rfbpzmjtjj\n9-10 p: kpppppppqpp\n3-15 k: hgkgrqblnjmsbvrghzdk\n6-7 r: rrtwrwlfrswwr\n3-4 k: fkkdv\n12-20 g: ggggggggkggggggggkgh\n3-5 x: xxxxpxxxxxxxxxb\n8-10 k: kkkkkkkzkh\n4-10 q: qqqmqqqqqcqq\n4-12 t: ttgtqblzqzpttcxdtfn\n18-20 s: sssbbsscsjvslsvsssss\n3-6 x: xxxxxxx\n4-5 t: tttttt\n5-7 q: xqqqqqqqxkqqqqqqqtqq\n2-4 s: zsvq\n6-7 b: mmbcklx\n1-13 z: zzfzkshpslwcn\n1-14 d: ddpddtdddddmmf\n8-13 j: jjjjjjjjjjjjjjjjjz\n5-6 c: cjjlxb\n10-12 v: vvvvvvtvvmvv\n2-3 b: wfbbg\n1-10 l: llllllllllllllllll\n5-10 d: hdbhdgxcjd\n10-13 t: ttttttttttttnt\n5-6 h: vhmhhh\n16-17 x: xxxxxxxxxxxxxxxsqx\n6-14 g: gzggggzhggggzgg\n15-20 f: ffbfxffffvfffffffhfd\n2-3 d: xdvw\n5-7 g: zgggjgq\n2-12 g: ggggggggmgggggg\n6-8 h: hhhhhhphhhlhjhhxfh\n3-10 j: knjklstqxwcsjf\n2-3 h: hvnwjxhpsc\n7-8 w: wwwlwwgw\n14-16 s: chgtpswssxsqtwzrsqt\n5-6 d: zdbdkl\n3-4 p: lwkpbrbp\n8-14 c: cbhtccjmrccrcp\n2-3 s: tctflmgdtsjjfxpl\n3-4 g: gggjkctwdsgl\n2-4 h: ghbh\n4-13 h: fczphhvflghhhd\n3-10 x: jkzhgljwsblcrmbwfx\n12-13 p: npfgppprzpppc\n7-12 l: sqbplmqnlmwph\n1-8 p: hpppvpprppp\n12-18 n: nhbznznxncnkcchscl\n9-10 g: grjxmgzpgk\n6-7 g: rcmkggggggg\n1-7 b: bbbbbbbb\n2-3 t: ttftzqt\n8-10 l: twbjlnblhck\n1-2 c: cvbc\n4-9 d: mfpdddsnd\n5-8 f: flpvbdrfl\n7-12 s: rhspsxlbpsmsclzrdsfc\n2-4 h: vhhh\n1-10 k: bhkkkkkkxkkkzk\n5-15 q: qqqqqqqqqqqqdqdqql\n12-15 c: ljvcklwjvngfgfgrjsv\n8-10 w: pwwtpbwwvwwrwww\n5-13 b: tbjmbfmknjhbb\n1-2 v: cssd\n2-5 s: ssssss\n3-4 h: bhhxxhfh\n3-5 p: pfppp\n13-20 l: lcxxllcfjmllclljllfl\n2-4 p: sqpzk\n2-4 l: xlllb\n1-7 t: ttttttrttttrttttttxt\n10-12 m: bwmmghzmqmmpmj\n5-8 l: jtlljpql\n5-6 d: lddddd\n6-8 c: nljczccdwvnmrlqvlsc\n8-9 d: dzdddcddt\n10-11 z: zzzzzzzzmqj\n3-4 s: msssss\n3-5 z: xzvdzbzt\n2-18 v: bvcbpwkbdmclbnbmsv\n9-12 b: jrcccsndstzbxprkvtq\n3-11 n: vlntglzvvcnngn\n3-8 v: rpgckwptlvdqsrqqt\n6-11 q: qdqdkqvkvhdrdqm\n9-12 b: khbmbgbbvbqb\n9-10 g: gtggggggczgg\n3-5 c: zqctcs\n15-18 z: kbzsdhbbzxfzzqdjzc\n7-8 g: ggggggzx\n9-10 s: sswssrssqms\n14-17 g: ggggcggggggggpggcgg\n10-15 g: qgzmbkjlggrhgkg\n9-11 j: jjjjjjjjqqjjjjjj\n5-6 c: cgcdcchcccbcc\n2-4 g: gggg\n5-13 h: plkhhrmxhxhmh\n11-16 v: vkkqrvbvbcvvnvvvvv\n6-7 c: rlfmqphqrhqkhch\n3-5 z: hzspz\n8-9 d: kddmdddpdvddln\n5-11 k: wkqkcfkpvnkvh\n4-7 r: fnzzwxrxr\n15-16 r: rrrrrrrrrrrrrrwr\n2-19 f: fvffffffffffffffffwf\n9-11 v: hbvbvvgcvvvj\n2-4 m: mrxpv\n13-14 z: zztzgzzpzzzzzg\n4-10 t: qtvtcrfmlkrgtwsvwtw\n3-7 g: ggggggfgggggggg\n5-8 c: ccccfcccnmccc\n6-12 z: bzfcjzdznzwzrzbzzqrn\n14-15 c: ccccccccccccczkccccc\n3-4 j: jjjjjjf\n1-2 x: kxxxx\n3-4 s: sksj\n17-19 c: hvchccvccdxgccnxdcc\n3-6 r: trrrrrrr\n10-11 c: ckcjzcrzcbc\n1-7 p: pmqplfpvgq\n3-4 h: hhmx\n5-6 n: nnwndln\n5-10 v: bkkvfgvqwdt\n7-14 h: hkjlwvhdnhxhwcnhs\n4-12 f: xxwfjfcwslrfzrxfkxj\n2-3 c: ccccc\n4-13 k: kkkdkkkkkkkkkkk\n8-10 b: mqlljkpbbbxbrbfx\n1-4 p: pppp\n14-17 n: nnnnnnnnnnnnnnnngnn\n5-6 d: dddddddvw\n9-10 r: rjtrrrmqrrrzrrrrjrrm\n7-11 x: bxxxxxrwxpmn\n13-14 w: gwmwwwlwwjwjwx\n2-4 d: dddddvddd\n7-9 p: pjmdppgpspcslh\n13-16 l: tllllzllpvllvlzd\n9-11 q: qzmwqqzqtqqq\n4-5 d: dvdngdd\n9-11 j: xxxvjrmgjpk\n3-4 c: rvvcn\n15-16 r: rxvmlslkpmqdqtdd\n2-5 b: sbflb\n4-5 w: wntpw\n8-16 n: nnnnnnnhnqnnnnnnn\n5-6 n: nnnnnnn\n2-5 q: bsjfhq\n6-18 r: prrrqkmrrrvbrrrrdfrv\n3-5 g: xnxlp\n4-11 s: jsstsssjssfssss\n14-16 d: dddddddddsdddddd\n18-19 n: vgngvbhdjfrbnznhhjzn\n11-13 f: ffffffffffgfgf\n5-11 n: dbgvngchnkngt\n3-4 f: gsff\n2-4 r: rfdrlznkzg\n10-18 z: zzgzwzzzzzzdzzmzhzzn\n4-5 s: qscmbssss\n11-15 x: xbxxxxgxxxbxxzw\n2-10 h: dkrnxknmthcv\n12-13 b: bbbbvbsbbbbgcbnb\n4-5 m: mmlmmsmmh\n3-6 j: njrjjkcr\n10-12 x: xxxxwdnxxxxx\n14-16 g: hggggggggngggggg\n8-9 x: gjqfxxxtxxxb\n16-17 f: ffffffffjffkffffmffb\n2-8 z: tsktkzfxntrv\n2-6 v: hvvswd\n1-2 w: xddl\n2-6 k: svwvvkqmzwjkx\n9-12 p: ppppppppbppspppb\n12-19 m: mdjmlhsmxmwcmmmmmmm\n3-5 x: rxjxb\n17-18 r: rrrrrrrrrrrrrrrrwk\n2-9 b: cbfbqcfwbmwd\n1-11 h: hhhhhbhhhhmvhhhh\n2-3 j: jkjjgjhxj\n5-6 f: fffffffffhf\n17-18 j: jprvtszvgsbtxlrhljsz\n1-4 m: mmhmmmp\n2-6 p: xvfkpkc\n1-2 f: xqtfcf\n1-5 r: brrrrrr\n7-10 w: wwhwwwdwwj\n1-4 w: vwwdh\n1-3 f: hnpvgfwth\n10-12 k: kkkkkkkkkdkq\n5-13 r: rrpqrrvrqrswzr\n9-17 x: xjxxvxvbtxxxtpxpx\n12-13 j: njrjkcjgbjjnj\n2-4 l: lvll\n12-17 p: dpppprpbppnvpppprp\n4-18 b: zftbmbxgzfzdvdnvhb\n12-16 r: lcqgqjthprlxrzrrx\n2-7 m: msclccmxhsmf\n3-4 b: bfkb\n2-6 k: skfgrk\n4-5 q: qqqhhx\n6-8 c: cccmvcch\n16-17 l: bxnlvbvwzvfvbcmxl\n7-12 c: cccccccccccc\n6-7 b: bbbbjsjbbbs\n2-7 q: phxnfxqrqv\n4-5 h: hhhhhh\n9-15 n: nqnnglsjnnghxrn\n3-4 h: hkkh\n5-11 r: rlsjvrrrrrl\n5-7 b: bbbbzbwbbbb\n2-3 f: qczff\n8-14 c: fccqccccccclccccck\n1-8 r: rrbfmjsr\n4-5 p: dpphz\n11-13 d: wdmwkcqddrdvdz\n8-15 h: hhhhhhhhhhhhhhhhh\n6-10 l: dhgdclhlkltnc\n14-15 t: tttntttnthtttzw\n4-6 p: pppppdp\n13-14 l: dvlnvlgtbpnhll\n11-12 s: brsqgfsnpmwskhdnm\n6-7 j: httjvjj\n2-14 w: wwwfwjzwrzfwnwjwwm\n8-9 w: wwrwwwwgt\n2-5 r: dvrwb\n7-9 b: wwvqbsbjb\n1-12 l: lljxlbslwlgn\n11-12 m: mmmmmmhmmmwbmkmmt\n10-11 l: lblllmllldl\n3-9 w: wwpwwwwbfwwwm\n9-10 q: pcrqfqlskz\n7-9 l: llvlllblklllx\n6-7 g: qgggjmwg\n5-10 v: vvvvvxbrvvp\n9-10 d: hqddgkkdrpdd\n17-18 q: qqqqqqqlqqqqqqqqdt\n6-10 h: hspnhhzldxphdh\n8-13 l: vvvscdnlblllml\n6-12 p: ppkrprpxwpppwx\n4-5 k: tkslb\n7-8 d: hdjdhnzdd\n9-10 x: wkxtblgxxjxxlqnfxxlx\n8-9 g: rggggggdk\n10-20 d: kcgdtbbswwdtvgdgxfwd\n3-4 g: gggggg\n16-17 l: llnzlqllllzllllmllll\n4-10 r: nsrrrbzrfzcrrzrrdqk\n4-6 k: kkkkkpkk\n4-8 n: nnnmntnnrnnn\n12-14 l: lllllllllwlqllll\n3-6 r: rrrrrrr\n1-6 s: ssskcshsxtd\n7-15 d: ndrbdnntdmkddxd\n9-10 j: pjjjjgjsjhjj\n7-10 k: pkbkkkkkgkq\n4-8 m: mmxmmmdmmmmm\n9-13 c: ngjcrcccvbcvqdjmph\n3-5 q: qqqqqq\n7-10 s: fcssnsssssslxspr\n3-5 k: kkkkvv\n5-10 f: tlbcvgwfzlf\n5-9 x: xxxxqxxxx\n2-10 q: qrprhbrhjhb\n3-4 g: gggmgg\n8-10 j: jjjjjjjgjpjjj\n2-4 v: dvzvvtfm\n7-12 q: zvzqprjhqdcqfzr\n12-13 f: ffffffffffffqf\n2-3 f: fffsdwq\n10-11 z: ztzzjzjzzzlzzz\n5-7 k: kkkkqtkkkkk\n6-9 z: dfbzhgsrzsp\n2-4 b: wpqb\n9-15 c: cwchcdhxlqzccxbb\n8-11 q: qqxptqqvqrgqg\n5-7 t: ttttztcvtjtkts\n2-6 n: nnbmdnjxclwkffrnxff\n3-7 p: pppppph\n9-12 d: xhmfndzcddfddvgddf\n1-5 h: hthmhvlthhhhh\n6-7 m: mmmmmmm\n6-7 j: jjjvjjjrj\n1-5 q: mqnqwqqqqqjq\n10-11 b: bmbbbdbpbbbbzbb\n1-4 k: gvccvdltkwcdd\n12-16 s: sssssssssssjssshs\n4-10 k: wzvlkmdhcklhdp\n10-13 z: zzzzzzzzzfzzj\n6-12 g: bmzmvvggpgtm\n3-11 m: nvcpfgvnsqmwxmmz\n1-5 n: dnwnnnnnnnbndnnn\n4-5 h: lhhhh\n4-5 g: gggdg\n2-5 h: xthqhfj\n2-4 f: lfkf\n4-6 k: rqbhrtzktmvmrxck\n4-5 q: jqdsc\n10-11 h: hhhhhhhhhvjh\n2-17 r: rrrrrrrprwrrrfjrr\n16-17 b: bbbbbbbbbbbbbbbbhbbb\n3-5 z: pkvzzfrljrjctw\n13-14 w: wwwwwwwjwwwwww\n8-10 d: dddddddldzpq\n1-4 b: sbbgb\n2-10 d: vnhpzmvpcddhs\n8-15 t: zftpwtrtqjqtfntp\n11-12 j: djjjjjjjcjjcj\n4-7 r: jrrtrrrvmzzrrvsl\n2-4 r: rpkp\n3-4 b: qbxc\n10-11 w: wwwwwwfzkdmnwwv\n6-14 z: mztxzzztmzwzzqvm\n12-14 w: wwwwwwmsgwdwsqwjwww\n6-10 f: zfhqjfhnjfdvwsfftf\n2-11 q: mwbbqdncdfq\n8-11 v: zvtvvwvfvvq\n6-8 n: xnwhzmdskwhn\n8-15 q: qqqqkqqqqqqqqqst\n1-4 n: nnnnn\n5-18 c: sqczchcwcccclccccccc\n3-4 j: klgr\n11-12 m: mmmmmmmmmmmm\n2-3 d: qdxfmqwbmdnvj\n1-5 m: cmmmm\n6-7 l: llllllll\n1-9 m: zmmmmmmmlmmmmmmm\n9-11 m: dwspwrmjsxpc\n5-6 p: qpppfz\n10-19 t: fvtphwzsptqzntbkxqt\n7-9 m: mpcmpmmmvptmm\n3-9 h: hhhpqqwhhtm\n11-16 p: pppppppppppppppvp\n1-9 k: ktkklkkkkkkk\n10-17 q: rthtqvgspqkvfkgkqfhj\n11-14 g: ggxgggggxggzggcnggg\n3-4 t: tttt\n9-10 t: sqtbdttthtttm\n1-4 t: ttkbgdzztbxd\n3-8 f: gxfcrrsfntftvffnfqff\n9-11 q: qqqqqqqjlqqq\n12-13 v: vvvvvvvvvvvvk\n7-11 b: nbxbbbsmbtkb\n7-8 k: kkkkkknn\n4-6 j: jjjjlv\n13-16 m: mmcmmmmmmmmmmmmvs\n7-9 k: kdkkkkrkgkk\n1-4 c: crcdlrdbzc\n1-16 k: kkkkkkkkkkgmkkktkkk\n2-12 l: llllrlllllllll\n4-9 g: gggbkgpgz\n6-7 q: qqqqqqjq\n6-7 v: hvvvvpm\n10-14 t: ttfxmqtgtttttbtct\n5-7 h: hwhhfrnch\n4-13 w: zdlrqvxwwzsfrfq\n4-5 h: hhhshhhh\n2-4 n: vhjfnz\n5-6 s: sssssss\n11-13 n: nnwrnnnlnngnn\n3-4 s: wbss\n3-4 s: wnss\n16-17 p: ppfprptkpmzkbjppp\n2-4 v: pvxv\n8-9 w: swwwwwwhwwnwwwxj\n4-6 s: pzhkvss\n4-5 x: wnxpx\n4-5 f: fsfwp\n4-5 z: zzdjz\n3-6 v: vnvvvvvvvvvvv\n5-6 f: wfxfff\n4-7 z: zzzqfzz\n3-5 q: qqjqsqqqf\n3-7 w: kwwkmww\n6-7 h: hhhhhhgh\n2-5 v: vvbzvvkn\n8-15 r: svrqpqrgrrhmzbms\n9-17 n: jnnnncnnnnnxnnnnnp\n5-6 n: dgnhsc\n5-7 l: lplltbv\n4-6 n: mmrnsbcqr\n7-8 w: wwjwwwwww\n1-16 c: bccccccfcccccccdcc\n6-9 l: sjnlmxwllg\n3-9 q: cqvhkwhtstwrl\n5-9 h: khvxhhhgfchhknhhhz\n8-9 c: ccccccccdc\n2-19 n: jjxbmbwmnqbblfbgzsz\n3-4 p: pppp\n1-3 b: jzxbbb\n3-6 h: hhhgjhhw\n2-8 f: vfxftzkmlzk\n11-19 h: mmdptdzhwdbjhvkccrhk\n4-14 s: sssvsdpsssssspns\n11-12 f: fffffffflfbk\n2-13 h: nhbgtbjvbpmrnhf\n1-3 t: ttttttttttttttttttm\n3-4 t: tsttr\n2-9 n: fnkknptqn\n1-8 j: qjpjjjjjjtpxjqjw\n2-12 c: clccccccccckccc\n18-19 f: dpftffzcfhqffddfpff\n1-4 j: trjj\n9-11 z: zwzmzzczsdd\n5-8 g: vrpbggfn\n2-5 k: qksvzkj\n2-4 f: wfdfjlfwmjrdmxx\n4-12 l: bnplnlgqcwql\n13-14 n: nqlfdnnnnnnnvbnwnlh\n4-8 l: dgxhsrql\n10-11 k: qvwcrkxtkjxlq\n5-6 g: ckgggg\n6-14 h: xbhhvzvxbhhhhhbhkzhh\n6-9 w: whwdwrxgc\n7-9 b: bbrbnjbfb\n7-8 w: brwwkfvwwwww\n2-11 g: gmggghngggg\n12-17 l: bgslljzntbmvtkbgllgg\n12-18 g: gggggggggggkggggggg\n1-3 l: gllllllll\n14-16 k: jkkqkkgkrkvxkkkkskgb\n2-3 v: vljvgnvm\n7-8 r: jrjvrprr\n7-8 f: xxffrnff\n2-18 j: jhjjjjjjjjjtjjfjjpjr\n8-9 q: rfllhmnqtrkv\n2-3 n: lnndv\n2-15 s: hzzsrprnnjlwdfs\n1-5 q: vqqqq\n9-18 b: gxbpbbppbrbbnlkmbb\n2-13 b: bbbbbbbbbbbbpb\n6-7 t: mtrftthtttftttztttst\n1-7 z: nfzzzzvzzczzzzzzzz\n4-12 m: mmmcmmmmmmmmmm\n9-11 p: jpswppqbmpfpzpg\n10-11 c: ccccccpcccc\n2-6 t: ztscdrkxxctdft\n3-13 q: mqfqqqqjqqfqdqqq\n16-20 z: zzjcxdmzgzzzppbtztzz\n5-15 w: wwwpwhwqcwwwwwgw\n2-4 p: pczp\n5-7 d: ddddmdfd\n2-3 f: fftc\n1-3 v: vvmv\n11-15 k: tjkcvkkkkgkzkkxqv\n4-6 b: vdkvbn\n7-8 c: wbcjhswc\n9-10 l: lllbqrllll\n4-5 s: sjnlw\n12-14 j: mjtmzfjjtsgvgtq\n1-3 l: llrllml\n12-13 d: dngddlqdtgdcd\n3-5 l: cblhld\n3-4 r: trrq\n1-3 c: kcccc\n9-10 g: bgbgjggpvgpgpggg\n3-6 d: szwlfm\n13-14 f: ffffffffffffzfff\n6-7 d: ddddddddddnnddddr\n7-16 h: xmbpwmhsznmldhnxflc\n4-6 q: qqqnqqqg\n2-7 t: ttwbpmnthmjr\n8-16 x: xbcxxbwxxrpxnfxd\n2-3 d: dngdd\n9-10 n: qkxfdljnnl\n4-5 f: ftfffff\n12-15 n: nnnnnncnnnnnnnsnn\n1-2 d: dkdd\n2-5 v: vvvvgv\n1-14 v: jvvvvvvvvvvvvmvvv\n5-6 r: rrrrrr\n2-3 b: bnvbbbtbjgxfchnkhcjb\n1-14 g: wjggxgggggggxgmrvcg\n1-6 x: bhvxhxxxx\n1-2 r: rprr\n6-7 c: cccccccqc\n4-8 b: bbgplbbcdtbbdbgbbhbz\n1-4 w: wjgw\n1-3 h: zhzzt\n2-11 j: sjjrtjkjhjj\n6-7 m: mlmrrmm") '[1 _ "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc"]
;  (fn [input]
;    (let [parse-line (fn [[range-str lett-str pass-str]]
;                       (let [[int-a int-b] (map #(Integer/valueOf %) (s/split range-str #"-"))
;                             [letter] (seq lett-str)]
;                         [int-a int-b letter pass-str]))
;          lines (map #(s/split % #"\s")
;                     (s/split-lines input))]
;      (map parse-line lines)))
;
;  (fn [lines]
;    (let [valid-pass? (fn [[min max letter pass]]
;                        (let [char-cts (->> (seq pass)
;                                            (group-by identity)
;                                            (map (fn [[c cs]] [c (count cs)]))
;                                            (into {}))
;                              ct (get char-cts letter 0)]
;                          (<= min ct max)))]
;      (count (filter valid-pass? lines))))
;
;  (fn [lines]
;    (let [valid-pass? (fn [[index-1-a index-1-b letter pass]]
;                        (let [pass-vec (vec pass)
;                              indices (map dec [index-1-a index-1-b])
;                              matches (filter #(= letter (pass-vec %)) indices)]
;                          (= 1 (count matches))))]
;      (count (filter valid-pass? lines))))
;
;  [2 1 "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc"]

