JSBFileGen
==========

JSB file generator für ExtJS 4

# Requirements
* orderd list of required javascript files for ExtJS Projects

# Parsing order
- Starting file path(s) given by argument (order is necessary)
- config file with mapping of package to filepath

## 1. find "app.js" in root
* find/parse "controllers: []"
* find/parse "requires: []"

## 2. parse js file
* define -> index of file
* extend: ''
* mixins: {key: "index of file"}
* models: [] // path is relative for app
* stores: [] // path is relative for app
* views: [] // path is relative for app
* requires: [] // path is full qualified

## 3. parse model file (identified by Ext.define() name)
* hasOne: ''
* hasMany: ''
* associations: []
** model: '' // path is full qualified (???)
** storeName: '' // path is relative

## 4. parse view file
* alias -> needed for detecting already loaded Ext.widget calls (see Always)
    
    
## N. Always try to find "app/index.html"
* find/parse html script tags
* parse all files not already found (should find configs, etc)
* prepend (!) to map

## Always.
* if commented out ignore (!) don't use
* find/parse "Ext.create()"
* find/parse "Ext.widget"
* find/parse "Ext.require()"
* find/parse alternate classnames
* more aliases

Result
------
1. Flat (?) map of required files containing (log file bamboo compatible ?)
    - location
    - existing
    - js object
    - several mapping/find parameter (widget)
    - file is used ? (list found but not used files)

2. Production ready file
    - Either
        * JSB3 file usable by sencha tools
        * replace all scripts tags in index.html with production file
    - Or
        * concat files in parsed/found order
        * Minify using YUI Compressor
        * replace all scripts tags in index.html with production file