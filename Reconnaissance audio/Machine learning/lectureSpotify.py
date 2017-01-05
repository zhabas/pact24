# -*- coding: utf-8 -*-
"""
Created on Thu Jan  5 00:08:49 2017

@author: Justin Dallant

Script pour lire des playlists de Spotify afin de constituer les bases de données pour le machine learning.
"""

import spotipy
import spotipy.util

#Récupération d'un token à partir de mon compte Spotify.
scope = 'user-library-read'
token = spotipy.util.prompt_for_user_token("Justin Dallant", scope, client_id ="82970e5b65af47109d4716fe423a9a1b", client_secret = "1645ada3c3cc4901a01d5f0d0a981428", redirect_uri = "http://localhost/")

#Connexion avec le token.
sp = spotipy.Spotify(auth=token)

#Exemple
search = 'electro'

#Recherche de playlists
results = sp.search(q=search, type='playlist')
items = results['playlists']['items']
#Je prends la première playlist, juste pour l'exemple. Il faudra probablement en prendre plusieurs ou les choisir à la main.
playlist = items[0]
user = playlist['owner']['id']
tracks = sp.user_playlist_tracks(user, playlist['id'], fields = "items", limit=10)['items']

#musicList de la forme [{'name' : ?, 'artist' : ?, 'tag' : ?, 'preview_url' : ?}, {...}, ...]
musicList = []
for i in tracks:
    music = {}
    track = i['track']
    music['name'] = track['name']
    music['artist'] = track['artists'][0]['name']
    music['tag'] = search
    music['preview_url'] = track['preview_url']
    musicList.append(music)