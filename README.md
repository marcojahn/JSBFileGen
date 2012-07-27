JSBFileGen
==========

JSB file generator für ExtJS 4

# Requirements
* orderd list of required javascript files for ExtJS Projects

# Parsing order
- Starting file path(s) given by argument (order is necessary)
- config file with mapping of package to filepath

## 1. find "app.js" in root
1.a. find/parse "controllers: []"
1.b. find/parse "requires: []"

## 2. parse js file
2.a. define -> index of file
2.b. extend: ''
2.c. mixins: {key: "index of file"}
2.d. models: [] // path is relative for app
2.e. stores: [] // path is relative for app
2.f. views: [] // path is relative for app
2.g. requires: [] // path is full qualified

## 3. parse model file (identified by Ext.define() name)
3.a. hasOne: ''
3.b. hasMany: ''
3.c. associations: []
3.c.1. model: '' // path is full qualified (???)
3.c.2. storeName: '' // path is relative

## 4. parse view file
4.a. alias -> needed for detecting already loaded Ext.widget calls (see Always)
    
    
## N. Always try to find "app/index.html"
N.a. find/parse <script type="text/javascript" src"..."></script"
N.b. parse all files not already found (should find configs, etc)
N.c. prepend (!) to map

## Always.
Always.a. if commented out ignore (!) don't use
Always.b. find/parse "Ext.create()"
Always.c. find/parse "Ext.widget"
Always.d. find/parse "Ext.require()"
Always.e. find/parse alternate classnames
Always.?. more aliases

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