JSBFileGen
==========

JSB file generator für ExtJS 4

Requirements
------------
* orderd list of required javascript files for ExtJS Projects

Parsing order
-------------
- Starting file path(s) given by argument (order is necessary)
- config file with mapping of package to filepath

1. find "app.js" in root
    a. find/parse "controllers: []"
    b. find/parse "requires: []"

2. parse js file
    a. define -> index of file
    b. extend: ''
    c. mixins: {key: "index of file"}
    d. models: [] // path is relative for app
    e. stores: [] // path is relative for app
    f. views: [] // path is relative for app
    g. requires: [] // path is full qualified

3. parse model file (identified by Ext.define() name)
    a. hasOne: ''
    b. hasMany: ''
    c. associations: []
        1. model: '' // path is full qualified (???)
        2. storeName: '' // path is relative

4. parse view file
    a. alias -> needed for detecting already loaded Ext.widget calls (see Always)
    
    
N. Always try to find "app/index.html"
    a. find/parse <script type="text/javascript" src"..."></script"
    b. parse all files not already found (should find configs, etc)
    c. prepend (!) to map

Always.
    a. if commented out ignore (!) don't use
    b. find/parse "Ext.create()"
    c. find/parse "Ext.widget"
    d. find/parse "Ext.require()"
    e. find/parse alternate classnames
    ?. more aliases

Result
------
1. Flat (?) map of required files containing (log file bamboo compatible ?)
    a. location
    b. existing
    c. js object
    d. several mapping/find parameter (widget)
    e. file is used ? (list found but not used files)

2. Production ready file
    a. Either
        1. JSB3 file usable by sencha tools
        3. replace all scripts tags in index.html with production file
    b. Or
        1. concat files in parsed/found order
        2. Minify using YUI Compressor
        3. replace all scripts tags in index.html with production file