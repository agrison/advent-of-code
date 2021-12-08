const L = [
    ['eadbcf faceb faecgd gdefabc adc ad adbf gfacbe bceda dcegb', 'gdfcae adc cedbfa dafb', 'cfdbgae', [0, 7, 9, 4], {eadbcf:9, faceb:5, faecgd:0, gdefabc:8, adc:7, ad:1, adbf:4, gfacbe:6, bceda:3, dcegb:2}],
    ['ed acegbfd defb ead dbcfae dbeca caefdg bgecfa dabgc efacb', 'gfecab ed cdaegf de', 'afdbgec', [6, 1, 0, 1], {ed:1, acegbfd:8, defb:4, ead:7, dbcfae:9, dbeca:3, caefdg:0, bgecfa:6, dabgc:2, efacb:5}],
    ['fda dfbeg cegdab fa edfagcb acgde dagfe abcgdf ceaf dfecag', 'fgcead af caedgbf bdfeagc', 'dcfebag', [9, 1, 8, 8], {fda:7, dfbeg:2, cegdab:6, fa:1, edfagcb:8, acgde:5, dagfe:3, abcgdf:0, ceaf:4, dfecag:9}],
    ['fdae cebgfa df bdf ebcgdf bcdeafg cfbdae afebc fbadc cbgda', 'df cbgfae eadf bfd', 'bedagfc', [1, 6, 4, 7], {fdae:4, cebgfa:6, df:1, bdf:7, ebcgdf:0, bcdeafg:8, cfbdae:9, afebc:5, fbadc:3, cbgda:2}],
    ['facgb gdefabc decbfa cb cba cgfaeb gafec bgfad agcefd bcge', 'cfdage bc dbagfce gcbfae', 'aebgdcf', [6, 1, 8, 9], {facgb:3, gdefabc:8, decbfa:0, cb:1, cba:7, cgfaeb:9, gafec:5, bgfad:2, agcefd:6, bcge:4}],
    ['eacfd caebdfg egfbac gaced abfde cf gafcde adbgce gcfd fec', 'fec ecf cf dgfc', 'egfdbca', [7, 7, 1, 4], {eacfd:3, caebdfg:8, egfbac:0, gaced:5, abfde:2, cf:1, gafcde:9, adbgce:6, gcfd:4, fec:7}],
    ['ecfgd fc fec cdfa defcag degbf dbcgaef eacgdb bfagce adgce', 'egcda cfdge cgefda dcfge', 'eafdbcg', [5, 3, 9, 3], {ecfgd:3, fc:1, fec:7, cdfa:4, defcag:9, degbf:2, dbcgaef:8, eacgdb:6, bfagce:0, adgce:5}],
    ['afbed eabgfc cbedag ac egdbfc gacf abcfe becgf aedfbgc cea', 'ca ac feabd fcag', 'egafdcb', [1, 1, 2, 4], {afbed:2, eabgfc:9, cbedag:0, ac:1, egdbfc:6, gacf:4, abcfe:3, becgf:5, aedfbgc:8, cea:7}],
    ['ecfgbd agebc dfbae fcedgab adgc gfbcae abcged dc cebad cbd', 'acdg begca cd dc', 'bgdafce', [4, 5, 1, 1], {ecfgbd:0, agebc:5, dfbae:2, fcedgab:8, adgc:4, gfbcae:6, abcged:9, dc:1, cebad:3, cbd:7}],
    ['cadeg gb dgaefbc gbd acbfde gabdfc gbcad afdbc abfg degbfc', 'fegcdba bg dbfceg gdb', 'dfgaebc', [8, 1, 0, 7], {cadeg:2, gb:1, dgaefbc:8, gbd:7, acbfde:6, gabdfc:9, gbcad:3, afdbc:5, abfg:4, degbfc:0}],
    ['ac dbagf dbcgfea acfgb gcad caf eacbdf dagbfc gcfbe efgabd', 'ac dgac egcfb deafbcg', 'fdcgeab', [1, 4, 2, 8], {ac:1, dbagf:5, dbcgfea:8, acfgb:3, gcad:4, caf:7, eacbdf:0, dagbfc:9, gcfbe:2, efgabd:6}],
    ['cafdbe gefdc fedba fagb gbfeda bgcfead ecgabd ga gda efdag', 'defga dag aegdf abfg', 'dbgfcae', [3, 7, 3, 4], {cafdbe:6, gefdc:2, fedba:5, fagb:4, gbfeda:9, bgcfead:8, ecgabd:0, ga:1, gda:7, efdag:3}],
    ['faceg ab gceab dgbefc bcegda bgfdace gecdb gbad bac bafdec', 'acgdbfe cebag edfgacb dabg', 'cdagfbe', [8, 3, 8, 4], {faceg:2, ab:1, gceab:3, dgbefc:6, bcegda:9, bgfdace:8, gecdb:5, gbad:4, bac:7, bafdec:0}],
    ['dcegaf faedbc acbdg dfcag afd gbafce efbgcda feacg df degf', 'fbagec acgbd daf gdfe', 'aedgbfc', [6, 2, 7, 4], {dcegaf:9, faedbc:0, acbdg:2, dfcag:3, afd:7, gbafce:6, efbgcda:8, feacg:5, df:1, degf:4}],
    ['agd dagbf fegbad bfdea bfgdc gedbcfa fdcaeg cfbeda ag bgea', 'bfaed adg dabgef gda', 'degbcaf', [5, 7, 9, 7], {agd:7, dagbf:3, fegbad:9, bfdea:5, bfgdc:2, gedbcfa:8, fdcaeg:0, cfbeda:6, ag:1, bgea:4}],
    ['fabgde gf cafbe bfg afcdebg cdfbge cfgd cebgf baecdg cgbde', 'fg fg gf fbg', 'bdfcage', [1, 1, 1, 7], {fabgde:0, gf:1, cafbe:2, bfg:7, afcdebg:8, cdfbge:9, cfgd:4, cebgf:3, baecdg:6, cgbde:5}],
    ['fgabdce gcfeda agcdbf gecaf ebagf ecbg bag fabed acefgb bg', 'gacfdb bag dcgeafb aecgf', 'acbedgf', [0, 7, 8, 5], {fgabdce:8, gcfeda:6, agcdbf:0, gecaf:5, ebagf:3, ecbg:4, bag:7, fabed:2, acefgb:9, bg:1}],
    ['gfdbea bedag gefdc bdca becagd fdbegac febagc bgc gbdce bc', 'gbdec cb caegdfb ebadg', 'gacdfbe', [3, 1, 8, 5], {gfdbea:6, bedag:5, gefdc:2, bdca:4, becagd:9, fdbegac:8, febagc:0, bgc:7, gbdce:3, bc:1}],
    ['agefdbc bdea ba fcbeg eacgb abgcfd gceafd cbeadg gab acedg', 'gfacde cfgdeba cefadg dfbgac', 'gdbefac', [6, 8, 6, 0], {agefdbc:8, bdea:4, ba:1, fcbeg:2, eacgb:3, abgcfd:0, gceafd:6, cbeadg:9, gab:7, acedg:5}],
    ['cbegfd bafd gbfcae ba acfgbed egcda dbcfg acb bgafcd agcdb', 'cab gfcdab degabfc abc', 'cfadebg', [7, 9, 8, 7], {cbegfd:6, bafd:4, gbfcae:0, ba:1, acfgbed:8, egcda:2, dbcfg:5, acb:7, bgafcd:9, agcdb:3}],
    ['gcbf cgadb cdeag ecadfgb fdebag afdbg bc cab cfgdba cefbda', 'cb bfdgeac cba cba', 'afcgebd', [1, 8, 7, 7], {gcbf:4, cgadb:3, cdeag:2, ecadfgb:8, fdebag:6, afdbg:5, bc:1, cab:7, cfgdba:9, cefbda:0}],
    ['dcfge cbadge acedf fge dgfceb dbegc geadfbc gf edagbf bgcf', 'cefadgb fge gfe efg', 'ebfcagd', [8, 7, 7, 7], {dcfge:3, cbadge:6, acedf:2, fge:7, dgfceb:9, dbegc:5, geadfbc:8, gf:1, edagbf:0, bgcf:4}],
    ['ebdagcf gadfce bd badef adgbce eabfg abd cebadf edacf bfcd', 'aedbf dfbc adbfgce bd', 'acbfgde', [3, 4, 8, 1], {ebdagcf:8, gadfce:6, bd:1, badef:3, adgbce:0, eabfg:2, abd:7, cebadf:9, edacf:5, bfcd:4}],
    ['eacfdb cef gcbeafd ce defgc bfadgc bgfdc fedag ebgc egcbfd', 'cfe bgcedf ec gfcde', 'fbegacd', [7, 9, 1, 3], {eacfdb:0, cef:7, gcbeafd:8, ce:1, defgc:3, bfadgc:6, bgfdc:5, fedag:2, ebgc:4, egcbfd:9}],
    ['dbefa fbge gedfba dbgcfa gb dgb dbgceaf dbgea cdfbea egdca', 'bdg afdbcg gb fbdagc', 'dfgecba', [7, 0, 1, 0], {dbefa:5, fbge:4, gedfba:9, dbgcfa:0, gb:1, dgb:7, dbgceaf:8, dbgea:3, cdfbea:6, egdca:2}],
    ['gafc dgeacb eaf af abefc ebgadf cefdb efagdcb fgeacb becga', 'af egdbacf af aefbc', 'egfcdab', [1, 8, 1, 3], {gafc:4, dgeacb:6, eaf:7, af:1, abefc:3, ebgadf:0, cefdb:2, efagdcb:8, fgeacb:9, becga:5}],
    ['dgbca dgaef beac dcage gbafcd cgebdfa fdbgce ce cge dcbage', 'bacgd caeb gbedcfa gec', 'gbeafcd', [5, 4, 8, 7], {dgbca:5, dgaef:2, beac:4, dcage:3, gbafcd:6, cgebdfa:8, fdbgce:0, ce:1, cge:7, dcbage:9}],
    ['ab efdgba cdab bfceg gfadc abg cgedaf dgafebc cgdbaf cfgab', 'fcbga gfbca ba edcfga', 'gdbceaf', [3, 3, 1, 6], {ab:1, efdgba:0, cdab:4, bfceg:2, gfadc:5, abg:7, cgedaf:6, dgafebc:8, cgdbaf:9, cfgab:3}],
    ['ecdbf dcgbafe gcbdef fcdaeg gdf bfega gdbc fdbge cedabf gd', 'dgcebaf gebdf dbgc dg', 'fcgbade', [8, 3, 4, 1], {ecdbf:5, dcgbafe:8, gcbdef:9, fcdaeg:0, gdf:7, bfega:2, gdbc:4, fdbge:3, cedabf:6, gd:1}],
    ['dbgac cbd aedcfb gfbad gfcdba bc afebgd gcbf gcdae bfgadec', 'gbcedaf cb fbcg fbcg', 'dfcgeba', [8, 1, 4, 4], {dbgac:3, cbd:7, aedcfb:0, gfbad:5, gfcdba:9, bc:1, afebgd:6, gcbf:4, gcdae:2, bfgadec:8}],
    ['dfagceb ed bgadfe bgafd gacfdb adef bgeac bgdae fcdgeb bde', 'baedg defa adef dbega', 'bfeacdg', [3, 4, 4, 3], {dfagceb:8, ed:1, bgadfe:9, bgafd:5, gacfdb:6, adef:4, bgeac:2, bgdae:3, fcdgeb:0, bde:7}],
    ['bgfeac fea aecdgf egfba abce begcfd ae gfecb bcafgde bdagf', 'afe bfdgec ceab ae', 'fcabdeg', [7, 6, 4, 1], {bgfeac:9, fea:7, aecdgf:0, egfba:3, abce:4, begcfd:6, ae:1, gfecb:5, bcafgde:8, bdagf:2}],
    ['egbcf afcegb dbcef ebgcdf cfead bacedgf db gdfbae cgbd edb', 'edb decagbf gfcaedb aecbgfd', 'egdcabf', [7, 8, 8, 8], {egbcf:5, afcegb:6, dbcef:3, ebgcdf:9, cfead:2, bacedgf:8, db:1, gdfbae:0, cgbd:4, edb:7}],
    ['gaed egfca eac bcfeg abdgfce eafgdc cfadg ea cbdfag aebcfd', 'dfegabc cea eacgf efbcda', 'cdegbaf', [8, 7, 3, 0], {gaed:4, egfca:3, eac:7, bcfeg:2, abdgfce:8, eafgdc:9, cfadg:5, ea:1, cbdfag:6, aebcfd:0}],
    ['agbcde edgc gadfb gcbea bcfdae cgbaef dca cd cagdb fdagcbe', 'ebgdca dca ecgd edcg', 'aedgfcb', [9, 7, 4, 4], {agbcde:9, edgc:4, gadfb:2, gcbea:5, bcfdae:0, cgbaef:6, dca:7, cd:1, cagdb:3, fdagcbe:8}],
    ['gfdceb cdefa geacdb befgacd gd abecg gcd badg deacg bfgeca', 'bgcadef gbad defacbg fdgabce', 'cbdafge', [8, 4, 8, 8], {gfdceb:0, cdefa:2, geacdb:9, befgacd:8, gd:1, abecg:5, gcd:7, badg:4, deacg:3, bfgeca:6}],
    ['bagfc gc cebaf bdagf gdbace ecgf gbcafde bdefac cbg eagbcf', 'acfbg gc fceg cgb', 'begfdca', [3, 1, 4, 7], {bagfc:3, gc:1, cebaf:5, bdagf:2, gdbace:0, ecgf:4, gbcafde:8, bdefac:6, cbg:7, eagbcf:9}],
    ['efc fgedb efcagd dfcab acgdbfe fbacdg ce bfecd aceb fecadb', 'abce defgb ce ec', 'faebgcd', [4, 2, 1, 1], {efc:7, fgedb:2, efcagd:0, dfcab:5, acgdbfe:8, fbacdg:6, ce:1, bfecd:3, aceb:4, fecadb:9}],
    ['gadcbef bdfe cgbeaf fea cdaebf acdfg fe ebcad ecdfa beacgd', 'efdb edfb ef ef', 'abfdgec', [4, 4, 1, 1], {gadcbef:8, bdfe:4, cgbeaf:0, fea:7, cdaebf:9, acdfg:2, fe:1, ebcad:5, ecdfa:3, beacgd:6}],
    ['aebfc ebcfgd dcegafb bfaced gb bcg egadc agcbef cgbae bgaf', 'gafb bcfdeg cdbgfe gabf', 'cfgadbe', [4, 0, 0, 4], {aebfc:5, ebcfgd:0, dcegafb:8, bfaced:6, gb:1, bcg:7, egadc:2, agcbef:9, cgbae:3, bgaf:4}],
    ['fgbce befacdg fb gafce bgcfea cedgfa bgcde fbag eacdbf bfe', 'bef gfbeca bf ebf', 'eabgdfc', [7, 9, 1, 7], {fgbce:3, befacdg:8, fb:1, gafce:5, bgcfea:9, cedgfa:6, bgcde:2, fbag:4, eacdbf:0, bfe:7}],
    ['efbag fd adcbef abegcd fdgecab gdbfe dcfg fbdgec gcedb dfe', 'efd gdcf dgfc df', 'ecfgadb', [7, 4, 4, 1], {efbag:2, fd:1, adcbef:0, abegcd:6, fdgecab:8, gdbfe:3, dcfg:4, fbdgec:9, gcedb:5, dfe:7}],
    ['cdeabf gcde gbfdae ec cfgab abgce cae bgcdae gdeab gfcdeba', 'eacbdf bcdfgae aegbc aec', 'adcgfeb', [0, 8, 3, 7], {cdeabf:0, gcde:4, gbfdae:6, ec:1, cfgab:2, abgce:3, cae:7, bgcdae:9, gdeab:5, gfcdeba:8}],
    ['bcafge bdae fdcge fbdaeg dgfae aef ea cgfedba gafcbd dabgf', 'ea aef deba aef', 'fbedcag', [1, 7, 4, 7], {bcafge:0, bdae:4, fdcge:2, fbdaeg:9, dgfae:3, aef:7, ea:1, cgfedba:8, gafcbd:6, dabgf:5}],
    ['egfd ebcgaf fbecg gd dcg cadfgb eabcdfg fgecbd cegdb ceabd', 'dfagecb fdbgcae bdcea fgecb', 'cfdeagb', [8, 8, 2, 5], {egfd:4, ebcgaf:6, fbecg:5, gd:1, dcg:7, cadfgb:0, eabcdfg:8, fgecbd:9, cegdb:3, ceabd:2}],
    ['bedcgfa dbecg abcfdg fd cdfeg fgace bdef gbfedc gdf gcadeb', 'dfg bgacdef cedfg dcgfeab', 'gbfeadc', [7, 8, 3, 8], {bedcgfa:8, dbecg:5, abcfdg:0, fd:1, cdfeg:3, fgace:2, bdef:4, gbfedc:9, gdf:7, gcadeb:6}],
    ['fcgdaeb eagb cabfe gb dgcef gcfeb gbc befgac fbacde adfbcg', 'gcdfab gb gdfabec beag', 'cagedbf', [0, 1, 8, 4], {fcgdaeb:8, eagb:4, cabfe:5, gb:1, dgcef:2, gcfeb:3, gbc:7, befgac:9, fbacde:6, adfbcg:0}],
    ['cgabfe badfge aegd gdfcb decbfag de dbgef befadc bagef dbe', 'fceagb bcfgdea becgaf gfedb', 'badgcef', [6, 8, 6, 3], {cgabfe:6, badfge:9, aegd:4, gdfcb:2, decbfag:8, de:1, dbgef:3, befadc:0, bagef:5, dbe:7}],
    ['fcd gedf fdaebcg abgcfd df faceb edacg gabedc fdcae afegcd', 'cbaged aefgdc fagbedc dfge', 'cgfebda', [6, 9, 8, 4], {fcd:7, gedf:4, fdaebcg:8, abgcfd:0, df:1, faceb:2, edacg:5, gabedc:6, fdcae:3, afegcd:9}],
    ['cedabg be edgca dbecgf cageb cedfag gfcedab cbgfa adbe egb', 'bdea egfabdc be gaecd', 'gdbafec', [4, 8, 1, 5], {cedabg:9, be:1, edgca:5, dbecgf:0, cageb:3, cedfag:6, gfcedab:8, cbgfa:2, adbe:4, egb:7}],
    ['bagfe faecgd bdgeaf eabgfc gdacb fbce cgf cgfebad fc gfabc', 'cebf bgdecaf fcg cgf', 'gecbdfa', [4, 8, 7, 7], {bagfe:5, faecgd:0, bdgeaf:6, eabgfc:9, gdacb:2, fbce:4, cgf:7, cgfebad:8, fc:1, gfabc:3}],
    ['acdfbe abcde dcegfba fegdc bgcade fdcea acefgb fa aef badf', 'fgadbce fa acdfe fedac', 'ebfdgac', [8, 1, 3, 3], {acdfbe:9, abcde:5, dcegfba:8, fegdc:2, bgcade:6, fdcea:3, acefgb:0, fa:1, aef:7, badf:4}],
    ['gbcef gefacdb fca afgcbe efgcad gbac ac dbcfge cfbae eabfd', 'bcfgeda ebdaf agbc ac', 'fgabdce', [8, 2, 4, 1], {gbcef:5, gefacdb:8, fca:7, afgcbe:9, efgcad:0, gbac:4, ac:1, dbcfge:6, cfbae:3, eabfd:2}],
    ['abdeg gdeafc bagdf gedbac fbge bdeafg gf fbcda fag gbdeacf', 'bgfdae bedcafg fcgade fegb', 'aefbcgd', [9, 8, 0, 4], {abdeg:5, gdeafc:0, bagdf:3, gedbac:6, fbge:4, bdeafg:9, gf:1, fbcda:2, fag:7, gbdeacf:8}],
    ['bcadg cbd edgcbf afdc bacfg cd cbagdf dgecfba egfacb bdega', 'abgde cafd cadf dcb', 'bfdaecg', [2, 4, 4, 7], {bcadg:3, cbd:7, edgcbf:0, afdc:4, bacfg:5, cd:1, cbagdf:9, dgecfba:8, egfacb:6, bdega:2}],
    ['gcb fbgcae dgfbc egdc bdagef abcfd gc fbdceg befgd eabcdfg', 'bgc cbfdgea gc edgc', 'becdagf', [7, 8, 1, 4], {gcb:7, fbgcae:0, dgfbc:3, egdc:4, bdagef:6, abcfd:2, gc:1, fbdceg:9, befgd:5, eabcdfg:8}],
    ['dgabe dbe daegf beac fgcbeda gecadb eb bdfgce cbgadf bacgd', 'gdfae abec bgdae eafdg', 'dceafbg', [2, 4, 3, 2], {dgabe:3, dbe:7, daegf:2, beac:4, fgcbeda:8, gecadb:9, eb:1, bdfgce:0, cbgadf:6, bacgd:5}],
    ['deagfcb gc cdga gafbde baefc egcdbf fadeg afcged gce eagcf', 'bcfdaeg egbfdca acdfegb cge', 'edcabgf', [8, 8, 8, 7], {deagfcb:8, gc:1, cdga:4, gafbde:6, baefc:2, egcdbf:0, fadeg:5, afcged:9, gce:7, eagcf:3}],
    ['adbfeg dafgb bgdacf gfbde fe fega bcgeafd beadcf fbe gdbec', 'gbefd agfe egfa fbe', 'baegcfd', [3, 4, 4, 7], {adbfeg:9, dafgb:5, bgdacf:6, gfbde:3, fe:1, fega:4, bcgeafd:8, beadcf:0, fbe:7, gdbec:2}],
    ['defbga fagdc fdace cfg gbfda eagbfc gdfaebc gc cdagfb dbcg', 'cg cg cbdg badfecg', 'fbcdega', [1, 1, 4, 8], {defbga:6, fagdc:3, fdace:2, cfg:7, gbfda:5, eagbfc:0, gdfaebc:8, gc:1, cdagfb:9, dbcg:4}],
    ['fbecdag gfdeb dgcbaf afcbed ec cfaebg acde ecb fdabc fedcb', 'bec cead ce adce', 'baedgcf', [7, 4, 1, 4], {fbecdag:8, gfdeb:2, dgcbaf:6, afcbed:9, ec:1, cfaebg:0, acde:4, ecb:7, fdabc:5, fedcb:3}],
    ['dfa gdbecf dfegc egafd cbaedf dfcebag ebgda dcegaf af cafg', 'eadfgbc daf efgad af', 'dcagbfe', [8, 7, 3, 1], {dfa:7, gdbecf:6, dfegc:5, egafd:3, cbaedf:0, dfcebag:8, ebgda:2, dcegaf:9, af:1, cafg:4}],
    ['gfdbe fdgcb gecfda egbfacd gfaed efb eafcgb be daeb bfdaeg', 'be feb bgedfa gdeaf', 'fabdceg', [1, 7, 9, 5], {gfdbe:3, fdgcb:2, gecfda:6, egbfacd:8, gfaed:5, efb:7, eafcgb:0, be:1, daeb:4, bfdaeg:9}],
    ['egcbfd bacfg bdfegca bfdgc edafbg cegba fagbdc adcf fa baf', 'abgfdc fagcdb af fcda', 'bdacefg', [9, 9, 1, 4], {egcbfd:6, bacfg:3, bdfegca:8, bfdgc:5, edafbg:0, cegba:2, fagbdc:9, adcf:4, fa:1, baf:7}],
    ['dfcebg afdgbc gaf ecga ga afgbe fabegc defcgab feabd cgbef', 'ceag dbcegf afg bfeagdc', 'fcaedgb', [4, 6, 7, 8], {dfcebg:6, afdgbc:0, gaf:7, ecga:4, ga:1, afgbe:3, fabegc:9, defcgab:8, feabd:2, cgbef:5}],
    ['adgbfe fbd beacfg fecd fabdc agbdc fcbeda df acefb fagbdce', 'cedf abcdefg dfabc dfb', 'bedcgfa', [4, 8, 3, 7], {adgbfe:0, fbd:7, beacfg:6, fecd:4, fabdc:3, agbdc:2, fcbeda:9, df:1, acefb:5, fagbdce:8}],
    ['cfaeb cgdeab fgced afdb cabedf db cabegf egdbfac cdb cefbd', 'dcb abcgef agdecb decfg', 'cadfgbe', [7, 6, 0, 2], {cfaeb:5, cgdeab:0, fgced:2, afdb:4, cabedf:9, db:1, cabegf:6, egdbfac:8, cdb:7, cefbd:3}],
    ['dcgbae aecbg agecf dface bcagdf fga cbfgae bgfe fadbceg fg', 'gf facbeg fg fg', 'abfedgc', [1, 9, 1, 1], {dcgbae:6, aecbg:5, agecf:3, dface:2, bcagdf:0, fga:7, cbfgae:9, bgfe:4, fadbceg:8, fg:1}],
    ['cafbdge bcgea af dcfage gbdcaf bafd fdbcg fga bafcg gbecdf', 'bacgdfe cafdbge cfagebd fgdebca', 'gdabefc', [8, 8, 8, 8], {cafbdge:8, bcgea:2, af:1, dcfage:0, gbdcaf:9, bafd:4, fdbcg:5, fga:7, bafcg:3, gbecdf:6}],
    ['fcabge afebg edgb dbgecaf abcfd fdg adgefc bgfda badfeg gd', 'febga ebdg gedb dfgba', 'fedbcga', [5, 4, 4, 3], {fcabge:6, afebg:5, edgb:4, dbgecaf:8, abcfd:2, fdg:7, adgefc:0, bgfda:3, badfeg:9, gd:1}],
    ['caefbd fgda fbcga gebca fbg cbfad dagfbc gf egdfbac fbgecd', 'feabdc dgaf gf gfdbeca', 'bdgaefc', [6, 4, 1, 8], {caefbd:6, fgda:4, fbcga:3, gebca:2, fbg:7, cbfad:5, dagfbc:9, gf:1, egdfbac:8, fbgecd:0}],
    ['egc bfcag gedcafb ebgcf fbdcge ceagfd bdcaef ge fdbce begd', 'afbcged cfgdaeb fabcde bgde', 'cdgbaef', [8, 8, 6, 4], {egc:7, bfcag:2, gedcafb:8, ebgcf:3, fbdcge:9, ceagfd:0, bdcaef:6, ge:1, fdbce:5, begd:4}],
    ['bcae gdcfbae egcbdf dfceb dfega ac agcbdf adfec adfebc cad', 'aceb gbcfad cagdbef ca', 'dbaegcf', [4, 0, 8, 1], {bcae:4, gdcfbae:8, egcbdf:6, dfceb:5, dfega:2, ac:1, agcbdf:0, adfec:3, adfebc:9, cad:7}],
    ['eg afge gefcabd cadgfb cgbae gedbcf ebg gbfcae abcfg ceadb', 'gadecbf dabecfg bge egb', 'bfeadgc', [8, 8, 7, 7], {eg:1, afge:4, gefcabd:8, cadgfb:6, cgbae:3, gedbcf:0, ebg:7, gbfcae:9, abcfg:5, ceadb:2}],
    ['cfge ecgadf eafgcdb cg gbecad fdacg cga cfbdae acfed fbdga', 'eabcgd fcaed gdacfe cefg', 'aegfbcd', [0, 5, 9, 4], {cfge:4, ecgadf:9, eafgcdb:8, cg:1, gbecad:0, fdacg:3, cga:7, cfbdae:6, acfed:5, fbdga:2}],
    ['ecgabfd fgbde cdfa da eacdgf fedga efagc dag abegdc gefacb', 'adfc faegcb dcafegb agd', 'gcdfbae', [4, 6, 8, 7], {ecgabfd:8, fgbde:2, cdfa:4, da:1, eacdgf:9, fedga:3, efagc:5, dag:7, abegdc:0, gefacb:6}],
    ['de cbdef eadc fcbdag fadcb gcbef befgda bdafce dfe gefbcda', 'fagdcb deafbc gaebdf gebafd', 'faecgdb', [6, 9, 0, 0], {de:1, cbdef:3, eadc:4, fcbdag:6, fadcb:5, gcbef:2, befgda:0, bdafce:9, dfe:7, gefbcda:8}],
    ['bdga dacbeg edg ebdacf fgacde dbceg bcaed becdagf gd cgebf', 'deg abgd egd gdeacbf', 'eagbfdc', [7, 4, 7, 8], {bdga:4, dacbeg:9, edg:7, ebdacf:6, fgacde:0, dbceg:3, bcaed:5, becdagf:8, gd:1, cgebf:2}],
    ['bcefg afge egfbac ebgfacd ef bedacf gdebc efc acgbf fagdcb', 'cadfgb efc cfe gdcbaf', 'caegdfb', [6, 7, 7, 6], {bcefg:3, afge:4, egfbac:9, ebgfacd:8, ef:1, bedacf:0, gdebc:2, efc:7, acgbf:5, fagdcb:6}],
    ['fd cfd fdcbge dgacb bacdeg fagbdc agfd befca fgdcaeb abfdc', 'df cgdbeaf bafcd aebdfgc', 'cgfaedb', [1, 8, 3, 8], {fd:1, cfd:7, fdcbge:0, dgacb:5, bacdeg:6, fagbdc:9, agfd:4, befca:2, fgdcaeb:8, abfdc:3}],
    ['be degfcab ebd abeg fcbdag eafcd gfebad defcbg dfbag badef', 'eafgcdb dcgebf bfdeagc edb', 'dgeacbf', [8, 0, 8, 7], {be:1, degfcab:8, ebd:7, abeg:4, fcbdag:6, eafcd:2, gfebad:9, defcbg:0, dfbag:5, badef:3}],
    ['dbfagc bgfcdae cagdbe bdcf geabfc fgb bfadg bf faged dbgac', 'afbcegd dcbf gdbca bcdf', 'gcfdeba', [8, 4, 5, 4], {dbfagc:9, bgfcdae:8, cagdbe:6, bdcf:4, geabfc:0, fgb:7, bfadg:3, bf:1, faged:2, dbgac:5}],
    ['ebgca abcegf cbgefd acg gfea cdefbga ga ecfgb cebad gfacbd', 'afge ag ecbfgda bdgcef', 'cfaedgb', [4, 1, 8, 6], {ebgca:3, abcegf:9, cbgefd:6, acg:7, gfea:4, cdefbga:8, ga:1, ecfgb:5, cebad:2, gfacbd:0}],
    ['agcfb facbe ef afgedc fegb efa dgabcf cedab eabgcf deacfbg', 'afe ecdbgaf gfbace cbegdaf', 'agebdfc', [7, 8, 9, 8], {agcfb:5, facbe:3, ef:1, afgedc:0, fegb:4, efa:7, dgabcf:6, cedab:2, eabgcf:9, deacfbg:8}],
    ['aegcdbf ecdb gfbadc afebg gdbcf de afcgde dgbefc def efgdb', 'geacfbd ebcd de dbec', 'fcebadg', [8, 4, 1, 4], {aegcdbf:8, ecdb:4, gfbadc:6, afebg:2, gdbcf:5, de:1, afcgde:0, dgbefc:9, def:7, efgdb:3}],
    ['gbe bacfg ebagc cbagef geadfb ge fagbdc deabcfg fcge cbdea', 'eadgbf gbcfa bcgaedf eg', 'bfecdga', [0, 5, 8, 1], {gbe:7, bacfg:5, ebagc:3, cbagef:9, geadfb:0, ge:1, fagbdc:6, deabcfg:8, fcge:4, cbdea:2}],
    ['geafc fgaced dgefbca gafed gde cbfgde aegbfc cagd gd daefb', 'dabcegf bgefcd dg cdga', 'ecdabgf', [8, 0, 1, 4], {geafc:5, fgaced:9, dgefbca:8, gafed:3, gde:7, cbfgde:0, aegbfc:6, cagd:4, gd:1, daefb:2}],
    ['fgbcd fabc fcdbeg gcdfa fcagedb gafed cag gfbcad ca cdgabe', 'faecgbd acg gdfbc aedgf', 'gbafecd', [8, 7, 5, 2], {fgbcd:5, fabc:4, fcdbeg:6, gcdfa:3, fcagedb:8, gafed:2, cag:7, gfbcad:9, ca:1, cdgabe:0}],
    ['gafcbe bca cedb cegfbad egabd bc abdgc afbdeg fdagc daebgc', 'gbdac gbacd dcbag cedb', 'aecdfbg', [3, 3, 3, 4], {gafcbe:0, bca:7, cedb:4, cegfbad:8, egabd:5, bc:1, abdgc:3, afbdeg:6, fdagc:2, daebgc:9}],
    ['ecg cg cfbeg gcdabe feagb cbefd fgac ebfcga gfdabec adefgb', 'gce gdebac bfedc gefba', 'eacfdgb', [7, 0, 2, 5], {ecg:7, cg:1, cfbeg:3, gcdabe:0, feagb:5, cbefd:2, fgac:4, ebfcga:9, gfdabec:8, adefgb:6}],
    ['febdc ebda feb dbecfa decgf gafceb fbcgad bdcagfe eb bdafc', 'edcfb fbe be cfbgda', 'faedgbc', [3, 7, 1, 6], {febdc:3, ebda:4, feb:7, dbecfa:9, decgf:2, gafceb:0, fbcgad:6, bdcagfe:8, eb:1, bdafc:5}],
    ['gfdcb bedfac abed cbdfgae cdabf afd faecdg cfbea caegbf da', 'da ad fad da', 'fedbgac', [1, 1, 7, 1], {gfdcb:2, bedfac:9, abed:4, cbdfgae:8, cdabf:3, afd:7, faecdg:0, cfbea:5, caegbf:6, da:1}],
    ['abfgdec bafgce fbdge bgfad de dcegfb cfaegd bdec deg gbfec', 'dge bfgecd dceb ecfgb', 'gcdbaef', [7, 9, 4, 5], {abfgdec:8, bafgce:6, fbdge:3, bgfad:2, de:1, dcegfb:9, cfaegd:0, bdec:4, deg:7, gbfec:5}],
    ['cdfe gcadeb afgdec egabf ce gecfa gcfda ceg ebfdcag gfadcb', 'cge dcef cdgfbea egc', 'gdefbca', [7, 4, 8, 7], {cdfe:4, gcadeb:0, afgdec:9, egabf:2, ce:1, gecfa:3, gcfda:5, ceg:7, ebfdcag:8, gfadcb:6}],
    ['da agfebc gdbcfa dafcg bdacgfe ecgfd dbfeac cad agfbc bgad', 'deabcfg dca bgcfa bdag', 'cbdgeaf', [8, 7, 5, 4], {da:1, agfebc:6, gdbcfa:9, dafcg:3, bdacgfe:8, ecgfd:2, dbfeac:0, cad:7, agfbc:5, bgad:4}],
    ['dgbfa efd afcbdg decbgaf fabe fedagb fe gdecfa cdbge fegbd', 'caedgfb dfe abfe dcbge', 'daebcfg', [8, 7, 4, 2], {dgbfa:5, efd:7, afcbdg:6, decbgaf:8, fabe:4, fedagb:9, fe:1, gdecfa:0, cdbge:2, fegbd:3}],
    ['gc acg cabfe cbeag bcfedga bagecd gdbae cgdb edfgca gbeadf', 'gac cgdb cg cdgfea', 'adcbfge', [7, 4, 1, 0], {gc:1, acg:7, cabfe:2, cbeag:3, bcfedga:8, bagecd:9, gdbae:5, cgdb:4, edfgca:0, gbeadf:6}],
    ['cdafbeg dfecgb gbadc bgcaef bfcge dbef gfbcd ceadfg dfg df', 'fd bdagc dfbgc gafdce', 'gedbafc', [1, 2, 3, 0], {cdafbeg:8, dfecgb:9, gbadc:2, bgcaef:6, bfcge:5, dbef:4, gfbcd:3, ceadfg:0, dfg:7, df:1}],
    ['dbgaf badfc badge ebcfad gf abcfgde cdabfg afcg cbegfd fgd', 'fg fg cfedbg fg', 'dcgaefb', [1, 1, 0, 1], {dbgaf:3, badfc:5, badge:2, ebcfad:6, gf:1, abcfgde:8, cdabfg:9, afcg:4, cbegfd:0, fgd:7}],
    ['fcgbe cbedafg deabfc gdacfe dega efdgc gd dacfe agdcfb fgd', 'dg eabdfcg dcfega cafged', 'fagebdc', [1, 8, 9, 9], {fcgbe:2, cbedafg:8, deabfc:6, gdacfe:9, dega:4, efdgc:3, gd:1, dacfe:5, agdcfb:0, fgd:7}],
    ['eadgcf cgebfa bfgd fbe afebdg dcagfbe bf cdeab fdgea abdef', 'dfgb bf fbead degafc', 'egbdcfa', [4, 1, 3, 6], {eadgcf:6, cgebfa:0, bfgd:4, fbe:7, afebdg:9, dcagfbe:8, bf:1, cdeab:2, fdgea:5, abdef:3}],
    ['cafgeb gadfb cabfgde df bdef dgf cadgb bfgae cfgdae dbefga', 'fdg gdf bgcda dfg', 'gedbcfa', [7, 7, 2, 7], {cafgeb:6, gadfb:3, cabfgde:8, df:1, bdef:4, dgf:7, cadgb:2, bfgae:5, cfgdae:0, dbefga:9}],
    ['gdfceb acefd gdafe ca acd bfac fbdce bgceda ebgfdca bcfead', 'acbf cbfa fabc dac', 'dbafgce', [4, 4, 4, 7], {gdfceb:6, acefd:3, gdafe:2, ca:1, acd:7, bfac:4, fbdce:5, bgceda:0, ebgfdca:8, bcfead:9}],
    ['cbfegd gfced befacdg fbgde afbgde dc cbdg decbfa gefac dec', 'dgcb dcefg edc cd', 'ebcgadf', [4, 3, 7, 1], {cbfegd:9, gfced:3, befacdg:8, fbgde:5, afbgde:6, dc:1, cbdg:4, decbfa:0, gefac:2, dec:7}],
    ['cg dfbca bdfeg agcd bacegfd cabfed cgdbf cgf bdgacf gcefab', 'dbegf bdagcf gc gcf', 'fagdecb', [2, 9, 1, 7], {cg:1, dfbca:5, bdfeg:2, agcd:4, bacegfd:8, cabfed:6, cgdbf:3, cgf:7, bdgacf:9, gcefab:0}],
    ['cafdg cfgbe efdagc fgbdc bdf bfegacd bd cfbadg bagefd cbad', 'db edagfc acdb dcgfea', 'fabcedg', [1, 6, 4, 6], {cafdg:5, cfgbe:2, efdagc:6, fgbdc:3, bdf:7, bfegacd:8, bd:1, cfbadg:9, bagefd:0, cbad:4}],
    ['fbagd cfga fcgeabd bfa fbged fecadb cfagbd abgcd egbcda af', 'gfacbde cagf abdfgc dabgcef', 'bcfgead', [8, 4, 9, 8], {fbagd:3, cfga:4, fcgeabd:8, bfa:7, fbged:2, fecadb:0, cfagbd:9, abgcd:5, egbcda:6, af:1}],
    ['fd gbeacf caegdf gdef cbafgd ecgfa daf bfdaecg faecd adcbe', 'bgcfad efbgacd afcged deacb', 'agdebfc', [0, 8, 9, 2], {fd:1, gbeacf:6, caegdf:9, gdef:4, cbafgd:0, ecgfa:5, daf:7, bfdaecg:8, faecd:3, adcbe:2}],
    ['fcaegd dfaec fgcbda cbaed fdcaeb eabcg cbd bagfdce db fedb', 'ebdf ebdf bdef fbcgad', 'cfbegda', [4, 4, 4, 0], {fcaegd:6, dfaec:5, fgcbda:0, cbaed:3, fdcaeb:9, eabcg:2, cbd:7, bagfdce:8, db:1, fedb:4}],
    ['fcdeb aebdgf cfgd gdbec cegbafd deg efgbdc bedcaf aecbg dg', 'gd ged gde dge', 'efgcadb', [1, 7, 7, 7], {fcdeb:5, aebdgf:0, cfgd:4, gdbec:3, cegbafd:8, deg:7, efgbdc:9, bedcaf:6, aecbg:2, dg:1}],
    ['agcf fecgab fc fgecb bcf abefg aefbdc ecdbfag dbgce gdefab', 'bdfcega decgb eabcfd fc', 'bacgdfe', [8, 2, 0, 1], {agcf:4, fecgab:9, fc:1, fgecb:3, bcf:7, abefg:5, aefbdc:0, ecdbfag:8, dbgce:2, gdefab:6}],
    ['bgcfd gfcad gfa adbegc afegcd gcead faegbd cefa abegcfd af', 'fa af gdcfb bgdcaef', 'gefcbad', [1, 1, 2, 8], {bgcfd:2, gfcad:3, gfa:7, adbegc:6, afegcd:9, gcead:5, faegbd:0, cefa:4, abegcfd:8, af:1}],
    ['efgb gadfbe bgdcae fdceab gafde dagbe gacfd fe dfe ebcdfag', 'fbeg fgeda dcfga dagcf', 'dbfgcea', [4, 3, 2, 2], {efgb:4, gadfbe:9, bgdcae:6, fdceab:0, gafde:3, dagbe:5, gacfd:2, fe:1, dfe:7, ebcdfag:8}],
    ['eacdgf gbaec fdbeag dg dafbegc adg gfcd faced daecg cbefad', 'dgaec dag cgfd afdec', 'afgcbde', [3, 7, 4, 5], {eacdgf:9, gbaec:2, fdbeag:0, dg:1, dafbegc:8, adg:7, gfcd:4, faced:5, daecg:3, cbefad:6}],
    ['ecbafg fgeab adecf abdgecf bafdcg gc cagfe gafbde cbge fcg', 'agbfde bgce egbc fegbad', 'fbcedga', [6, 4, 4, 6], {ecbafg:9, fgeab:5, adecf:2, abdgecf:8, bafdcg:0, gc:1, cagfe:3, gafbde:6, cbge:4, fcg:7}],
    ['decagf bedacf gabcfde abf deba cfgeb ba afced gabfdc beacf', 'ba ab eafdcg daecf', 'fdbegac', [1, 1, 6, 5], {decagf:6, bedacf:9, gabcfde:8, abf:7, deba:4, cfgeb:2, ba:1, afced:5, gabfdc:0, beacf:3}],
    ['fdgbaec ac fcba bdfec agedb adgfec cad adbcef cadeb egbcfd', 'edcbfa dca fbca acd', 'dfabgce', [9, 7, 4, 7], {fdgbaec:8, ac:1, fcba:4, bdfec:5, agedb:2, adgfec:0, cad:7, adbcef:9, cadeb:3, egbcfd:6}],
    ['agfebdc agbfcd bd fegacd abcge bda bgdf bcdeaf dgafc gdacb', 'dfceab dcabg adcefb fbdcea', 'afbgedc', [0, 3, 0, 0], {agfebdc:8, agbfcd:9, bd:1, fegacd:6, abcge:2, bda:7, bgdf:4, bcdeaf:0, dgafc:5, gdacb:3}],
    ['faebd bdcge eca fdbecg dgac ca fecdagb feacbg ebgdac daecb', 'gcad cadeb eac aec', 'egadfcb', [4, 3, 7, 7], {faebd:2, bdcge:5, eca:7, fdbecg:6, dgac:4, ca:1, fecdagb:8, feacbg:0, ebgdac:9, daecb:3}],
    ['abgecf cabgf efgcad cgf adebfg fadbc bgce gc gfeab ecfdagb', 'gbaef gbfae ebcg cg', 'fecbdga', [5, 5, 4, 1], {abgecf:9, cabgf:3, efgcad:0, cgf:7, adebfg:6, fadbc:2, bgce:4, gc:1, gfeab:5, ecfdagb:8}],
    ['ecg dcfebg degaf cg gefac gecbdaf cafedg gadc cafeb adfgeb', 'cge dbeacgf dbfcgae bgcefd', 'edcabgf', [7, 8, 8, 0], {ecg:7, dcfebg:0, degaf:5, cg:1, gefac:3, gecbdaf:8, cafedg:9, gadc:4, cafeb:2, adfgeb:6}],
    ['adgfbe fcabegd da daecfg afd begaf cdfeb bdag afbcge ebafd', 'gabd dgba bgda fdabge', 'fgdbcae', [4, 4, 4, 9], {adgfbe:9, fcabegd:8, da:1, daecfg:0, afd:7, begaf:5, cdfeb:2, bdag:4, afbcge:6, ebafd:3}],
    ['bfgac bfdcega dfeg abgedc cadbef df adcfg fdeacg egdac fcd', 'df gefd edgf df', 'cefgbda', [1, 4, 4, 1], {bfgac:2, bfdcega:8, dfeg:4, abgedc:6, cadbef:0, df:1, adcfg:3, fdeacg:9, egdac:5, fcd:7}],
    ['cbe efcgbd gcdfe cafedg be cebdgfa gbeafc cadgb dbgce bdef', 'ecdbfag debf cbe fdeb', 'cfbdaeg', [8, 4, 7, 4], {cbe:7, efcgbd:9, gcdfe:5, cafedg:6, be:1, cebdgfa:8, gbeafc:0, cadgb:2, dbgce:3, bdef:4}],
    ['begfc acbfd gd gbfcade efdabg fdg ecbgdf cbdgf gebcfa egcd', 'defgbac dgf gdf fgaceb', 'fedcagb', [8, 7, 7, 6], {begfc:5, acbfd:2, gd:1, gbfcade:8, efdabg:0, fdg:7, ecbgdf:9, cbdgf:3, gebcfa:6, egcd:4}],
    ['befacd efbdgc gfcabde ade gfadce da bdeaf ebgaf bfdec dacb', 'dbafegc cabd dea edafb', 'ecabgdf', [8, 4, 7, 3], {befacd:9, efbdgc:6, gfcabde:8, ade:7, gfadce:0, da:1, bdeaf:3, ebgaf:2, bfdec:5, dacb:4}],
    ['cfd abgfc befgcad agdf dcefgb dfacbg abgefc bfdac bcdae fd', 'afgd badfegc cbeda cfd', 'cgdaefb', [4, 8, 2, 7], {cfd:7, abgfc:5, befgcad:8, agdf:4, dcefgb:0, dfacbg:9, abgefc:6, bfdac:3, bcdae:2, fd:1}],
    ['gcbefd bcgfa ad dgae fedgab dba fabgdce gafbd fbgde fdceba', 'afdbg da fgbad ecfdgb', 'beagcdf', [3, 1, 3, 6], {gcbefd:6, bcgfa:2, ad:1, dgae:4, fedgab:9, dba:7, fabgdce:8, gafbd:3, fbgde:5, fdceba:0}],
    ['dabc bdcefa acebf bfcagde cb cfb fbaeg gdefbc caedf dcgaef', 'fbega fcb cb cb', 'fdbagce', [2, 7, 1, 1], {dabc:4, bdcefa:9, acebf:3, bfcagde:8, cb:1, cfb:7, fbaeg:2, gdefbc:0, caedf:5, dcgaef:6}],
    ['beadc gdafce ea dacfb ace fgabdc cdgbe dbacfge eadfcb ebfa', 'ae aecfdb bgacdef beaf', 'cfebgad', [1, 9, 8, 4], {beadc:3, gdafce:0, ea:1, dacfb:5, ace:7, fgabdc:6, cdgbe:2, dbacfge:8, eadfcb:9, ebfa:4}],
    ['dgf ebgcad cbdfag gf bfgde cfgbdae dfbea cfge gbfecd bcged', 'cegbfad cfeg dgf gfd', 'dcfeagb', [8, 4, 7, 7], {dgf:7, ebgcad:6, cbdfag:0, gf:1, bfgde:3, cfgbdae:8, dfbea:2, cfge:4, gbfecd:9, bcged:5}],
    ['fadeg abedfc abgdfec efg cgefba decfa fgadb cgedfa cedg ge', 'gedc dcge gefda gefbca', 'fcgdbea', [4, 4, 3, 0], {fadeg:3, abedfc:6, abgdfec:8, efg:7, cgefba:0, decfa:5, fgadb:2, cgedfa:9, cedg:4, ge:1}],
    ['abfecd dcegaf caebfgd gaf bcaeg gcfae defagb dcgf dcfea fg', 'fg afg beacgfd faebgcd', 'adgcbfe', [1, 7, 8, 8], {abfecd:6, dcegaf:9, caebfgd:8, gaf:7, bcaeg:2, gcfae:3, defagb:0, dcgf:4, dcfea:5, fg:1}],
    ['acdbg cbgead efbgdc acfd cbdfgae bdfag cbafgd fgbea df dgf', 'begaf fgd fdac fgd', 'gcfaedb', [2, 7, 4, 7], {acdbg:5, cbgead:6, efbgdc:0, acfd:4, cbdfgae:8, bdfag:3, cbafgd:9, fgbea:2, df:1, dgf:7}],
    ['dc cfd badgfc fbgda acdb agdbfe fgcda gdfecb fegacbd fcega', 'dcf fgbeda cdfaebg cdf', 'fbcaedg', [7, 6, 8, 7], {dc:1, cfd:7, badgfc:9, fbgda:5, acdb:4, agdbfe:6, fgcda:3, gdfecb:0, fegacbd:8, fcega:2}],
    ['adg geab cgbed dbgca debagc dfabc cebgdf ag eadcbfg adefcg', 'bcgda bgeadc ag bega', 'deabfgc', [3, 9, 1, 4], {adg:7, geab:4, cgbed:5, dbgca:3, debagc:9, dfabc:2, cebgdf:6, ag:1, eadcbfg:8, adefcg:0}],
    ['fge fbgced efbcg cafged fe aegbdfc edgcb gecadb fbed fbgac', 'gfe cebgd agbdcfe gfe', 'gdfbaec', [7, 5, 8, 7], {fge:7, fbgced:9, efbcg:3, cafged:0, fe:1, aegbdfc:8, edgcb:5, gecadb:6, fbed:4, fbgac:2}],
    ['cgfabed ge egf fabged agcbf adge ebafd cfbgde dfbcae abgef', 'gead feg egfab ebgcfd', 'fdgaceb', [4, 7, 3, 0], {cgfabed:8, ge:1, egf:7, fabged:9, agcbf:2, adge:4, ebafd:5, cfbgde:0, dfbcae:6, abgef:3}],
    ['faecdbg ecadb fbagce cbf fb acgef agfdce fcadgb cabfe befg', 'befg fbge fb gfadce', 'cgbedfa', [4, 4, 1, 6], {faecdbg:8, ecadb:2, fbagce:9, cbf:7, fb:1, acgef:5, agfdce:6, fcadgb:0, cabfe:3, befg:4}],
    ['bdagce cedfb dfac bac gfcdbe cdegfab cfeab abegf ca caedfb', 'afgedcb acgdfbe bca dfcgeb', 'bdafgce', [8, 8, 7, 6], {bdagce:0, cedfb:5, dfac:4, bac:7, gfcdbe:6, cdegfab:8, cfeab:3, abegf:2, ca:1, caedfb:9}],
    ['fcbda fgd agecdf bcedgfa dbag dg fgcdb bacfdg ecfbg bacefd', 'bdfca fcadb bgefc dfg', 'fagbedc', [5, 5, 2, 7], {fcbda:5, fgd:7, agecdf:0, bcedgfa:8, dbag:4, dg:1, fgcdb:3, bacfdg:9, ecfbg:2, bacefd:6}],
    ['fegabcd efagd bdcega dc geacdf gfbca fgacd dgc fdeagb fdce', 'fced gdc fcgadbe cdg', 'gecfbda', [4, 7, 8, 7], {fegabcd:8, efagd:5, bdcega:0, dc:1, geacdf:9, gfbca:2, fgacd:3, dgc:7, fdeagb:6, fdce:4}],
    ['daecb dacefbg gcabde cfaed df fcbd bfgade aecdfb geacf dfe', 'bcfd adgefbc fd bcfd', 'ebfcgda', [4, 8, 1, 4], {daecb:5, dacefbg:8, gcabde:6, cfaed:3, df:1, fcbd:4, bfgade:0, aecdfb:9, geacf:2, dfe:7}],
    ['gabecd df begad fcbdeg dfab efbdga fdg gdeaf acfeg gafbcde', 'df badfeg fd fdgcbea', 'gbfacde', [1, 9, 1, 8], {gabecd:6, df:1, begad:5, fcbdeg:0, dfab:4, efbdga:9, fdg:7, gdeaf:3, acfeg:2, gafbcde:8}],
    ['efcag fegda ec efc gafdeb afbcg dbcgef agbdfec fedgac eacd', 'cfe fdgbec cgfedba ecf', 'fdcabeg', [7, 0, 8, 7], {efcag:3, fegda:5, ec:1, efc:7, gafdeb:6, afbcg:2, dbcgef:0, agbdfec:8, fedgac:9, eacd:4}],
    ['fegcdb gecfda daebcf ca bagfecd dcfbe adc dgbfa bfcad beca', 'baec adc ecgbafd acd', 'deabgcf', [4, 7, 8, 7], {fegcdb:6, gecfda:0, daebcf:9, ca:1, bagfecd:8, dcfbe:5, adc:7, dgbfa:2, bfcad:3, beca:4}],
    ['geacdf fgb fgabdc bfcgd edfcabg gdafc defgba bf bcedg fbca', 'aecdfg fcdga agdbfce gbf', 'gabcefd', [6, 5, 8, 7], {geacdf:6, fgb:7, fgabdc:9, bfcgd:3, edfcabg:8, gdafc:5, defgba:0, bf:1, bcedg:2, fbca:4}],
    ['edabf aceb bcadef ecfdb eagdf gadfcb dbfcgae ebcfdg ba dba', 'cbae abcfgde dcbef ecafgdb', 'dcaegbf', [4, 8, 5, 8], {edabf:3, aceb:4, bcadef:9, ecfdb:5, eagdf:2, gadfcb:0, dbfcgae:8, ebcfdg:6, ba:1, dba:7}],
    ['dgfecb efgdb cbe gdec gbfac bcdafe fbcdeag cebgf eabgdf ce', 'ce cdbeaf edgc eafbcdg', 'bdcgaef', [1, 0, 4, 8], {dgfecb:9, efgdb:5, cbe:7, gdec:4, gbfac:2, bcdafe:0, fbcdeag:8, cebgf:3, eabgdf:6, ce:1}],
    ['cegfd dbcfg acdebf bdeg bcfdge ecfabgd defacg fbd cfbag bd', 'ecbdaf cdebfa fdecag cdgfe', 'febgadc', [0, 0, 6, 5], {cegfd:5, dbcfg:3, acdebf:0, bdeg:4, bcfdge:9, ecfabgd:8, defacg:6, fbd:7, cfbag:2, bd:1}],
    ['agcf cegfda ca ebfda bgcdfe fecda egfdc cbgfead adc dcegba', 'adc cfagbed afcg bdeaf', 'dgafbce', [7, 8, 4, 2], {agcf:4, cegfda:9, ca:1, ebfda:2, bgcdfe:6, fecda:3, egfdc:5, cbgfead:8, adc:7, dcegba:0}],
    ['gcabd gabefc dbfeag cbgedf de fdaebgc efcd bed cdebg bcgef', 'baecgf cbdga defc de', 'bfdcaeg', [6, 2, 4, 1], {gcabd:2, gabefc:6, dbfeag:0, cbgedf:9, de:1, fdaebgc:8, efcd:4, bed:7, cdebg:3, bcgef:5}],
    ['fcgbae gedc fdbgac egcdaf gdefbca dbeaf dgf gd acgef deafg', 'gfd dfg gcde dbefa', 'fcdebga', [7, 7, 4, 2], {fcgbae:6, gedc:4, fdbgac:0, egcdaf:9, gdefbca:8, dbeaf:2, dgf:7, gd:1, acgef:5, deafg:3}],
    ['efadc fedgcb egf dgfabc bgae eg bdfage fagbd dgafe bgdafec', 'gbea agbe dbgecfa geafd', 'fbeacgd', [4, 4, 8, 3], {efadc:2, fedgcb:0, egf:7, dgfabc:6, bgae:4, eg:1, bdfage:9, fagbd:5, dgafe:3, bgdafec:8}],
    ['deabcf bg gfcbaed gacfb gcb fdacgb afceg gdcefb cfdba dagb', 'fbdagec bcg cdfabge bcg', 'cdgaebf', [8, 7, 8, 7], {deabcf:6, bg:1, gfcbaed:8, gacfb:3, gcb:7, fdacgb:9, afceg:2, gdcefb:0, cfdba:5, dagb:4}],
    ['cfba gdeab cfdga fbd cdgfeb gecdbaf dcagfe bf facdbg fbdag', 'acbf fgdab abdge facb', 'dcbaefg', [4, 3, 2, 4], {cfba:4, gdeab:2, cfdga:5, fbd:7, cdgfeb:0, gecdbaf:8, dcagfe:6, bf:1, facdbg:9, fbdag:3}],
    ['fagdbec dgc fgcb dgeaf fcbeda cebgad dgefc egbfcd gc fcebd', 'egcfd gc facdgbe cadebf', 'dbgface', [3, 1, 8, 6], {fagdbec:8, dgc:7, fgcb:4, dgeaf:2, fcbeda:6, cebgad:0, dgefc:3, egbfcd:9, gc:1, fcebd:5}],
    ['cfgbae cbgdef egfcb ecfgbda fcagb bagedc gdafb ac abc cefa', 'daebfgc degacb gcebfad acfe', 'beafdcg', [8, 0, 8, 4], {cfgbae:9, cbgdef:6, egfcb:5, ecfgbda:8, fcagb:3, bagedc:0, gdafb:2, ac:1, abc:7, cefa:4}],
    ['edgac bdfeca acg baecd cdagefb ecfdg geacdb eabfgc gabd ag', 'abdg agc cebfad adgb', 'cbgdfae', [4, 7, 6, 4], {edgac:3, bdfeca:6, acg:7, baecd:5, cdagefb:8, ecfdg:2, geacdb:9, eabfgc:0, gabd:4, ag:1}],
    ['fdgcae cd bcefa cedg adgbef cdf fdeag dbfgca facde gefadbc', 'dcf geafd edcg dceg', 'fgcebda', [7, 5, 4, 4], {fdgcae:9, cd:1, bcefa:2, cedg:4, adgbef:6, cdf:7, fdeag:5, dbfgca:0, facde:3, gefadbc:8}],
    ['gbead cb aecbd ebdfcag bcd fadce geacbd ebfgda bdfceg acbg', 'bdc egbdca dagbe cdb', 'dgcafbe', [7, 9, 5, 7], {gbead:5, cb:1, aecbd:3, ebdfcag:8, bcd:7, fadce:2, geacbd:9, ebfgda:6, bdfceg:0, acbg:4}],
    ['dgeac feacdg fdea cda fgadcb gfcae ecgfab fceagbd da edgbc', 'acfebdg da fcabdeg da', 'cfdebag', [8, 1, 8, 1], {dgeac:3, feacdg:9, fdea:4, cda:7, fgadcb:0, gfcae:5, ecgfab:6, fceagbd:8, da:1, edgbc:2}],
    ['fdbeagc cge dabegc geacd cg bfdecg fecad gbca eabgd fdbgea', 'cg gc ecg ceg', 'ebcafgd', [1, 1, 7, 7], {fdbeagc:8, cge:7, dabegc:9, geacd:3, cg:1, bfdecg:0, fecad:2, gbca:4, eabgd:5, fdbgea:6}],
    ['ac acdeg fedbag facgedb cfedg edbga cedgab dabc fbgcae ace', 'fgdecba badegc dacb dacb', 'ebcdfag', [8, 9, 4, 4], {ac:1, acdeg:3, fedbag:6, facgedb:8, cfedg:2, edbga:5, cedgab:9, dabc:4, fbgcae:0, ace:7}],
    ['ecfa gcefdb fgecad gadbf adgfebc gcdfa bagdce ac acd gdfce', 'adc ac fbdaecg fgacd', 'deafbcg', [7, 1, 8, 3], {ecfa:4, gcefdb:6, fgecad:9, gadbf:2, adgfebc:8, gcdfa:3, bagdce:0, ac:1, acd:7, gdfce:5}],
    ['ebcad bfdagec abgf dfgcb af cegafd bfdca gfdacb adf dcfbge', 'fcgebad febcdg af bgfced', 'dgabefc', [8, 6, 1, 6], {ebcad:2, bfdagec:8, abgf:4, dfgcb:5, af:1, cegafd:0, bfdca:3, gfdacb:9, adf:7, dcfbge:6}],
    ['gfdb dabcg cdgbaf ecdgbaf bfcad deabcf gad gd agfedc bgaec', 'ebgac gbfd dg gfbd', 'afgbedc', [2, 4, 1, 4], {gfdb:4, dabcg:3, cdgbaf:9, ecdgbaf:8, bfcad:5, deabcf:6, gad:7, gd:1, agfedc:0, bgaec:2}],
    ['fdegac gedca dce gadebf cegab cd cbegadf gaefd dcgebf fdac', 'edfabgc afcegd defcgb gbdcfe', 'efcabdg', [8, 9, 0, 0], {fdegac:9, gedca:3, dce:7, gadebf:6, cegab:2, cd:1, cbegadf:8, gaefd:5, dcgebf:0, fdac:4}],
    ['fdebag bedcgaf cadefg fdeb fe fea adbeg cbedag bgfac aefbg', 'ecdfbga agfcbde eaf bedacg', 'adfbceg', [8, 8, 7, 6], {fdebag:9, bedcgaf:8, cadefg:0, fdeb:4, fe:1, fea:7, adbeg:5, cbedag:6, bgfac:2, aefbg:3}],
    ['cabegfd deabg bc cfdb bfcgda afdgec bgc febgac fgacd gbdca', 'dcabg gbc dgfbeca bgc', 'gfbdeca', [3, 7, 8, 7], {cabegfd:8, deabg:2, bc:1, cfdb:4, bfcgda:9, afdgec:6, bgc:7, febgac:0, fgacd:5, gbdca:3}],
    ['dbgfac gc dcg cbafde dgafce aceg fgdeb fegdc edafc dgbefac', 'fdecagb cg gc cg', 'dagebcf', [8, 1, 1, 1], {dbgfac:0, gc:1, dcg:7, cbafde:6, dgafce:9, aceg:4, fgdeb:2, fegdc:3, edafc:5, dgbefac:8}],
    ['afcgd degbac dgcab edgcfba dcefab gecbaf gdeb bg dcbea abg', 'begd gab bged bg', 'aegdfbc', [4, 7, 4, 1], {afcgd:2, degbac:9, dgcab:3, edgcfba:8, dcefab:6, gecbaf:0, gdeb:4, bg:1, dcbea:5, abg:7}],
    ['dfbegca afd afdeb ebfac agbd agdfec bgcefd agdbef bfegd ad', 'ecfbgd da abcef fbcegd', 'fgabcde', [6, 1, 2, 6], {dfbegca:8, afd:7, afdeb:3, ebfac:2, agbd:4, agdfec:0, bgcefd:6, agdbef:9, bfegd:5, ad:1}],
    ['dbecaf agdfe bgcde dgefba fb gbedcfa feb fgedb cdefga fagb', 'dgaef afbecd bcdge efgdabc', 'eabgcfd', [5, 0, 2, 8], {dbecaf:0, agdfe:5, bgcde:2, dgefba:9, fb:1, gbedcfa:8, feb:7, fgedb:3, cdefga:6, fagb:4}],
    ['fgcd beafdg gdfbcae becgd gfb edcgfb abcfe acgbed ebcgf fg', 'dfbgaec gfdc gfedcba becgd', 'bdfcage', [8, 4, 8, 5], {fgcd:4, beafdg:0, gdfbcae:8, becgd:5, gfb:7, edcgfb:9, abcfe:2, acgbed:6, ebcgf:3, fg:1}],
    ['dacgbf abedg febcdg cbafg ce afedbcg feca gbfaec gebca ebc', 'ce efca aecgfb cfabg', 'bfeadcg', [1, 4, 9, 5], {dacgbf:6, abedg:2, febcdg:0, cbafg:5, ce:1, afedbcg:8, feca:4, gbfaec:9, gebca:3, ebc:7}],
    ['dagfe cfae fgaced dgfebc fegbcda cf cfgad cdf adbfge dcbag', 'cafgde cfd fc gefda', 'decabfg', [9, 7, 1, 5], {dagfe:5, cfae:4, fgaced:9, dgfebc:0, fegbcda:8, cf:1, cfgad:3, cdf:7, adbfge:6, dcbag:2}],
    ['bcgdefa dfaceb fbgeac bgcfe ebag dcfeg fadbgc bef bafcg be', 'fgeabcd be fcgab gecbf', 'faegdbc', [8, 1, 5, 3], {bcgdefa:8, dfaceb:0, fbgeac:9, bgcfe:3, ebag:4, dcfeg:2, fadbgc:6, bef:7, bafcg:5, be:1}],
    ['acgfbe cbgda gaebdfc fegd bdfcg df fdb cebdfa fegdbc fbceg', 'adbfceg dcfeba fdb df', 'bedgafc', [8, 0, 7, 1], {acgfbe:6, cbgda:2, gaebdfc:8, fegd:4, bdfcg:3, df:1, fdb:7, cebdfa:0, fegdbc:9, fbceg:5}],
    ['df cgdaf efagc cbfd cegbadf cdbag agdfeb cdbgaf dcbage gdf', 'dfg fgd fdg dgf', 'gbfceda', [7, 7, 7, 7], {df:1, cgdaf:3, efagc:2, cbfd:4, cegbadf:8, cdbag:5, agdfeb:0, cdbgaf:9, dcbage:6, gdf:7}],
    ['fgcdab dcbafe badfe gf befg gfead gaedc abcegfd fga abdefg', 'bgadef fdbaecg gf befg', 'abgecfd', [9, 8, 1, 4], {fgcdab:0, dcbafe:6, badfe:5, gf:1, befg:4, gfead:3, gaedc:2, abcegfd:8, fga:7, abdefg:9}],
    ['gcedfb ecbagf acbgfd gafe begfc afc dabce cedfgab af eacfb', 'afge af bfcea baegcf', 'cgaedfb', [4, 1, 3, 9], {gcedfb:6, ecbagf:9, acbgfd:0, gafe:4, begfc:5, afc:7, dabce:2, cedfgab:8, af:1, eacfb:3}],
    ['gfcdaeb ecbfgd gd fbceg dfcba bcdgf afgbce gbd fdgabe dcge', 'gd gcde aegfcbd ebfgc', 'bedcagf', [1, 4, 8, 5], {gfcdaeb:8, ecbfgd:9, gd:1, fbceg:5, dfcba:2, bcdgf:3, afgbce:6, gbd:7, fdgabe:0, dcge:4}],
    ['gbfade befcd bfagec bdaef daebcf cbfgd ec ceb ecad edabfgc', 'ce ceda fdbce ec', 'bacdgef', [1, 4, 3, 1], {gbfade:6, befcd:3, bfagec:0, bdaef:5, daebcf:9, cbfgd:2, ec:1, ceb:7, ecad:4, edabfgc:8}],
    ['ceagdf gfbae gecfb dabg cedfba abfdecg deafgb afedb ga afg', 'bagef fdegca ebfcgad decfba', 'fdgbcae', [3, 0, 8, 6], {ceagdf:0, gfbae:3, gecfb:2, dabg:4, cedfba:6, abfdecg:8, deafgb:9, afedb:5, ga:1, afg:7}],
    ['baged fgace cdeb efdabg abcdfg cb abcegd agdfbec abc egacb', 'bced abc bac fcaeg', 'adcefbg', [4, 7, 7, 2], {baged:5, fgace:2, cdeb:4, efdabg:6, abcdfg:0, cb:1, abcegd:9, agdfbec:8, abc:7, egacb:3}],
    ['bedcfg gfbedca egdfca cadef dac da gbaced feabc adgf cefdg', 'gdecbfa dabgec cad abecf', 'cgafbde', [8, 0, 7, 2], {bedcfg:6, gfbedca:8, egdfca:9, cadef:3, dac:7, da:1, gbaced:0, feabc:2, adgf:4, cefdg:5}],
    ['cebdag dfgc fc efc caedgfb gcead ecgfda ebafg fegac bacefd', 'afegb cef ecfgbda gecfabd', 'edfgbca', [2, 7, 8, 8], {cebdag:6, dfgc:4, fc:1, efc:7, caedgfb:8, gcead:5, ecgfda:9, ebafg:2, fegac:3, bacefd:0}],
    ['afge aebcg eg dfcbae gce bcefa agbcd gcdebf cebagf abfdecg', 'geaf ceabg abfce gafe', 'cfgadeb', [4, 3, 5, 4], {afge:4, aebcg:3, eg:1, dfcbae:6, gce:7, bcefa:5, agbcd:2, gcdebf:0, cebagf:9, abfdecg:8}],
    ['gdcebfa cgeadf cgd gd fdag befcdg fecda bdcafe cebga gaedc', 'febdcg gd bdefcga fadg', 'cfgabde', [0, 1, 8, 4], {gdcebfa:8, cgeadf:9, cgd:7, gd:1, fdag:4, befcdg:0, fecda:5, bdcafe:6, cebga:2, gaedc:3}],
    ['afdcgb gadbfe cebda gacfbde ecdf edb ed agceb bcafd dcfabe', 'bgcea bde dfec acfgedb', 'bfecgda', [2, 7, 4, 8], {afdcgb:6, gadbfe:0, cebda:3, gacfbde:8, ecdf:4, edb:7, ed:1, agceb:2, bcafd:5, dcfabe:9}],
    ['ceabg cagbfd adbcg adcegb dcbe acdbfeg geafbd eb bae cgfae', 'be bae fecag agcdb', 'adecfbg', [1, 7, 2, 5], {ceabg:3, cagbfd:6, adbcg:5, adcegb:9, dcbe:4, acdbfeg:8, geafbd:0, eb:1, bae:7, cgfae:2}],
    ['eafcdg de fadgcb bdafe gbfae bdfca abedcf dea aefdcbg becd', 'afdbcge eda ed aed', 'acebgdf', [8, 7, 1, 7], {eafcdg:0, de:1, fadgcb:6, bdafe:3, gbfae:2, bdfca:5, abedcf:9, dea:7, aefdcbg:8, becd:4}],
    ['fdecg dgeaf fgc ebgfdca gedfba egac cdgaef dfgcab fdebc cg', 'acge gace cg edgfc', 'facebgd', [4, 4, 1, 3], {fdecg:3, dgeaf:5, fgc:7, ebgfdca:8, gedfba:6, egac:4, cdgaef:9, dfgcab:0, fdebc:2, cg:1}],
    ['gf dcgae cdabeg gafd gefadc cgeaf cabfe degcfba gfc bcdgef', 'gfc ecdgafb adgf dgeca', 'cdfabge', [7, 8, 4, 5], {gf:1, dcgae:5, cdabeg:6, gafd:4, gefadc:9, cgeaf:3, cabfe:2, degcfba:8, gfc:7, bcdgef:0}],
    ['efbcd cafegb fb gedfabc bcf cedfgb fbdg cfeda cdgeb cbedag', 'cbf fcb bgced bfc', 'cgfdabe', [7, 7, 5, 7], {efbcd:3, cafegb:0, fb:1, gedfabc:8, bcf:7, cedfgb:9, fbdg:4, cfeda:2, cdgeb:5, cbedag:6}],
    ['afcbe aedbf aebfgd bcaeg ecf fc cafd eacfbd gfdecb bgcafed', 'bedcgf cfad ebacg dfgcbe', 'edcagfb', [0, 4, 2, 0], {afcbe:3, aedbf:5, aebfgd:6, bcaeg:2, ecf:7, fc:1, cafd:4, eacfbd:9, gfdecb:0, bgcafed:8}],
    ['dcg cgbed dbfcag abcedf gcae fedcabg egfbd eabgcd gc aebdc', 'cgd ecag dcg cg', 'dagefcb', [7, 4, 7, 1], {dcg:7, cgbed:3, dbfcag:0, abcedf:6, gcae:4, fedcabg:8, egfbd:2, eabgcd:9, gc:1, aebdc:5}],
    ['fcabged abgdc gac bgafcd gdfab gabefd cdeab cg gadfec cbfg', 'edcbafg acg dbacegf gbfc', 'afcbegd', [8, 7, 8, 4], {fcabged:8, abgdc:3, gac:7, bgafcd:9, gdfab:5, gabefd:6, cdeab:2, cg:1, gadfec:0, cbfg:4}],
    ['cdf feacb dfbac dbfcga bfgda adgc dc fdebga gcefdb dbaegfc', 'gbeafd fabdcg dcf cd', 'fgcaedb', [6, 9, 7, 1], {cdf:7, feacb:2, dfbac:3, dbfcga:9, bfgda:5, adgc:4, dc:1, fdebga:6, gcefdb:0, dbaegfc:8}],
];

function setup() {
    frameRate(60);
    createCanvas(900, 1000);
}

let N = 1000;

function textyellow() {
    stroke('rgb(204, 204, 204)');
    fill('rgb(204, 204, 204)');
}

function textblue() {
    stroke('lightblue');
    fill('lightblue');
}

let K = [...L];
let T = L.map(_ => 100);
let U = L.map(_ => 100);

function draw() {
    background("#10101a");
    textyellow();

    stroke('rgb(204, 204, 204)');
    fill('rgb(255, 255, 102)')
    textSize(18);
    textFont('Consolas');
    let sum = 0
    for (let i = 0; i < L.length; ++i) {
        let y = N + i * 18;
        if (y > 700) { // initial
            stroke('lightgreen');
            fill('lightgreen');
            text(L[i][0] + " | " + L[i][1], 10, y);
        } else if(y > 300 && y < 700) { // unscrambled
            let change = T[i]-- % 10 == 0;
            const newtext = L[i][0].split(" ").map(e => change && Math.random() < 0.5 ? (Object.keys(L[i][4]).includes(e) ? L[i][4][e] : e) : e);

            K[i][0] = newtext.join(" ");
            stroke('rgb(204, 204, 204)');
            fill('rgb(204, 204, 204)')
            text(K[i][0] + " | " + L[i][1] , 10, y);

            textblue();
            text(L[i][2] , 750, y);
            textyellow();

        } else if (y < 300) { // solution
            let change = U[i]-- % 10 == 0;
            const newtext = L[i][1].split(" ").map((u,e) => change && Math.random() < 0.7 ? L[i][3][u] : e);
            K[i][1] = newtext.join(" ")

            stroke('rgb(255, 255, 102)');
            fill('rgb(255, 255, 102)');
            text(K[i][0] + " | " + L[i][3].join(" ") , 10, y);
            sum += parseInt(L[i][3].join(""))
        }
    }
    stroke('magenta');
    fill('magenta');
    text("output: " + sum.toLocaleString(), 600, 50)

    stroke('rgb(255, 255, 102)');
    strokeWeight(1);
    line(0, 290, 1000, 290);
    stroke('lightgreen');
    line(0, 690, 1000, 690);
    strokeWeight(0);

    N-=4;
}