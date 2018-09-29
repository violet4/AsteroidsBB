Graphics 800,600,32,3
Const ESCKEY = 1, SPACEBAR = 57, UPKEY = 200, LEFTKEY = 203, DOWNKEY = 208, RIGHTKEY = 205 
AppTitle "Asteroids::By Kyle Eldridge"
AutoMidHandle True
asdf=LoadImage("images/asteroid.bmp")
ScaleImage asdf,0.5,0.5
SeedRnd(MilliSecs())
SetBuffer BackBuffer()
Type bullet
	Field bstring
	Field x,y
End Type
sf=LoadImage("images/stars far.bmp")
sc=LoadImage("images/stars close.bmp")
Type hi
	Field x,y
	Field img
End Type
newa=0
AutoMidHandle True
bs=LoadImage("images/rocket.bmp")
ScaleImage bs,0.5,0.5
Type ship
	Field shippic
	Field hp
	Field x,y
	Field score
End Type
Type asteroid
	Field x,y
	Field hp
	Field pic
	Field xspeed
	Field yspeed
End Type
Global playerscore=0
a.asteroid=New asteroid
y=y+1
a\x=400
a\y=100
a\hp=1
a\xspeed=Rnd(-5,5)
a\yspeed=Rnd(1,10)
a\pic=asdf

startx=400
starty=300
starthp=10
Global p.ship=New ship
p\x=startx
p\y=starty
p\hp=starthp
p\score=0
AutoMidHandle True
p\shippic=LoadImage("images/ship2.bmp")
DrawImage p\shippic,p\x,p\y
ScaleImage p\shippic,2,2





While Not KeyDown(1)

Cls
TileImage sc,scx,scy
TileImage sf,sfx,sfy
scx=scx+3
scy=scy+2
sfx=sfx+2
sfy=sfy+1
newa=newa+1
If newa>=10
	a.asteroid=New asteroid
	y=y+1
	a\x=Rnd(0,800)
	a\y=-50
	a\pic=asdf
	a\xspeed=Rnd(-3,3)
	a\yspeed=Rnd(5,10)
	a\hp=1
	newa=0
EndIf
DrawHUD()

If KeyDown(LEFTKEY)
	p\x = p\x-7
	If p\x <=-40
		p\x =850
	EndIf
EndIf
If KeyDown(RIGHTKEY)
	p\x = p\x + 7
	If p\x >= 820
		p\x = -20
	EndIf
EndIf
If KeyDown(UPKEY)
	p\y = p\y - 7
	If p\y <= -20
		p\y = 620
	EndIf
EndIf
If KeyDown(DOWNKEY)
	p\y = p\y + 7
	If p\y >= 620
		p\y = -20
	EndIf
EndIf
If KeyHit(SPACEBAR)
	b.bullet=New bullet
	x=x+1
	b\x=p\x+4
	b\y=p\y-19
	b\bstring=bs
EndIf
If KeyDown(ESCKEY)
	End
EndIf

For b.bullet=Each bullet
	nob=nob+1
	b\bstring=bs
	b\y=b\y-7
	DrawImage b\bstring,b\x,b\y
	If b\y<=0
		Delete b
		playerscore=playerscore-10
	EndIf
	If nob>1
		Delete b
	EndIf
Next
nob=0

	For a.asteroid=Each asteroid
	a\x=a\x+a\yspeed
	a\y=a\y-a\xspeed
	If a\x<=0-ImageWidth(a\pic)
		a\x=800+ImageWidth(a\pic)
	ElseIf a\x>=800+ImageWidth(a\pic)
		a\x=0-ImageWidth(a\pic)
	Else If a\y>=680
		a\x=-50
	EndIf
	DrawImage a\pic,a\x,a\y
	For b.bullet=Each bullet
		 If ImagesCollide(a\pic,a\x,a\y,0,b\bstring,b\x,b\y,0)
			Delete a
			Delete b
			p\score=p\score+20
			playerscore=playerscore+20
		EndIf
		
	Next
	Next
DrawImage bs,p\x-4,p\y-19
DrawImage p\shippic,p\x,p\y

DrawHUD()
Flip

Wend




Function TestBPictures()
	For b.bullet=Each bullet
		abc=b\bstring
	Next
End Function

Function TestBX()
	For b.bullet=Each bullet
		bca=b\x
	Next
End Function

Function TestBY()
	For b.bullet=Each bullet
		cab=b\y
	Next
End Function




Function DrawHUD()
	Text 600, 10, "X position: " + p\x
	Text 600, 20, "Y position: " + p\y
	Text 600,30,"Score: "+playerscore
End Function




























































