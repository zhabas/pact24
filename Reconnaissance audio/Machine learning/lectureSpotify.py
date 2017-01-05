# -*- coding: utf-8 -*-
"""
Created on Thu Jan  5 00:08:49 2017

@author: Justin Dallant

Script pour lire des playlists de Spotify afin de constituer les bases de 
données pour le machine learning.
"""

import spotipy
import spotipy.util
import pickle
import os
import requests



###Connection à Spotify.###
###############################################################################
sp = spotipy.Spotify()
token = False
def connect():
    global sp 
    global token
    scope = 'user-library-read'
    token = spotipy.util.prompt_for_user_token(
            "Justin Dallant", scope, 
            client_id ="82970e5b65af47109d4716fe423a9a1b", 
            client_secret = "1645ada3c3cc4901a01d5f0d0a981428", 
            redirect_uri = "http://localhost/")
    
    sp = spotipy.Spotify(auth=token)
###############################################################################


###Gestion des données.###
###############################################################################
"""Enregistrer un objet dans un fichier."""
def save_obj(obj, name):
    with open(name + '.pkl', 'wb') as f:
        pickle.dump(obj, f, pickle.HIGHEST_PROTOCOL)
        
"""Charger un objet dans un fichier."""
def load_obj(name):
    with open(name + '.pkl', 'rb') as f:
        return pickle.load(f)
        
"""Enregistrer un mp3 dans un fichier."""       
def save_mp3(url, filename):
    request = requests.get(url, timeout=10, stream=True)
    with open(filename+'.mp3', 'wb') as fh:
        for chunk in request.iter_content(1024 * 1024):
            fh.write(chunk)
###############################################################################        


###Actions Spotify.###
###############################################################################
"""Fait une recherche de playlists sur Spotify"""
def search_playlists(search):
    if not token:
        connect()
    results = sp.search(q=search, type='playlist')
    return results['playlists']

"""Enregistre infos et mp3 (de 30s) d'une liste de playlists avec une limite 
de morceaux par playlist et une limite de morceaux au total.
"""
def save_playlists(playlists, tag, listLimit = 15, totalLimit = 100):
    if not token:
        connect()
    #On crée les bons dossiers s'ils n'existent pas
    if not os.path.exists(tag):
        os.makedirs(tag)
    if not os.path.exists(tag+"/Infos"):
        os.makedirs(tag+"/Infos")
    if not os.path.exists(tag+"/Mp3"):
        os.makedirs(tag+"/Mp3")
    
    #On ouvre/initialise les infos generales sur le dossier
    fileId = 0
    general = {}
    try:
        general = load_obj(tag+"/general")
        fileId = general['number']
    except Exception:
        general['number'] = 0
        general['tracks'] = []
    
    #Enregistrement des données utiles   
    num = 0
    for playlist in playlists['items']:
        if num>=totalLimit:
            break
        user = playlist['owner']['id']
        l = min(listLimit,totalLimit-num)
        tracks = sp.user_playlist_tracks(user, playlist['id'], 
                                         fields = "items", limit=l)['items']
        for i in tracks:
            music = {}
            track = i['track']
            music['name'] = track['name']
            music['artist'] = track['artists'][0]['name']
            music['tag'] = search
            music['preview_url'] = track['preview_url']
            if music['preview_url'] != None:
                general['tracks'].append(music)
                save_obj(music, tag+"/Infos/"+str(fileId))
                save_mp3(music['preview_url'], tag+"/Mp3/"+str(fileId))
                num+=1
                fileId+=1
    save_obj(general, tag+"/general")
###############################################################################


#Exemple
search = "energetic"                    
playlists = search_playlists(search)
save_playlists(playlists, search)

  

        