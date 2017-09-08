# Gank

### Architecture
This application is based on MVP + rxjava +rxAndroid + retrofit + Glide +cardeview and other.

### work for cutting duplicate code
*  make model impl extend basemodel and implement corresponding interface
* use baseView interface and basePresenter interface
* make corresponding MVP interface extend corresponding interface
* first level and second level UI have different name, and they have no much direct contact
* In first level UI, to cut duplicatecode, I have same name for different module,it means that it isn't traditional MVP
 
 
 ### MIT License
*Copyright (c) 2017 lilei1lll*

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 
