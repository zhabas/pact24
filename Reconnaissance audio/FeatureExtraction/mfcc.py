# -*- coding: utf-8 -*-
"""
Created on Sat Jan  7 22:19:40 2017

@author: Justin Dallant

Calcul de MFCC.
"""
import math
import numpy as np


def hamming(n,N):
    """Applique à n une fenêtre de hamming de largeur N."""
    return 0.54 - 0.46*math.cos(2*math.pi*n/(N-1))

def mel(f):
    """Convertit la fréquence f de hertz vers des mels."""
    return 1000*math.log(1+f/1000, 2)
    
def invmel(m):
    """Convertit la fréquence f de mels vers des hertz."""
    return (math.exp(0.69314718056/1000*m)-1)*1000
    
def triang(a,b,n):
    """Applique à n un filtre triangulaire entre a et b."""
    if a<=n<=(a+b)/2:
        return 2*(n-a)/(b-a)
    if (a+b)/2<=n<=b:
        return 2*(n-a)/(b-a)
    return 0
    

def decoupage(f_min, f_max, nb):
    """Crée un découpage en nb bandes entre les fréquences f_min et f_max,
    régulièrement espacées sur l'échelle de Mel.
    """
    mel_min, mel_max = mel(f_min), mel(f_max)
    ecart = (mel_max-mel_min)/nb
    return [invmel(mel_min+i*ecart) for i in range(nb+1)]    
 
   
def mfcc(S,f_echantillonage, N = 0, M = 0, nb_cepstres = 13, nb_filtres = 26, dK = 10):
    """Calcul les mfcc du signal S échantillonné à f_echantillonage.
    ---Paramètres---
    N           : largeur d'une trame.
    M           : décallage entre les trames.
    nb_cepstres : nombre de coefficients cepstraux à calculer.
    nb_filtres  : nombre de filtres triangulaires à appliquer.
    dK          : nombre de points à prendre en compte avant et après chaque
                  coefficient cepstral pour le calcul de dérivées.
    """
    nb_trames = len(S)//(N-M)
    d_trame = 0
    f_trame = N
    t_mfcc = []
    d_mfcc = []
    d2_mfcc = []
    
    #On crée un découpage régulier sur l'échelle de mel qu'on associe aux echantillons correspondants.
    h = decoupage(300,8000,nb_filtres)
    for j in range(nb_filtres+1):
        h[j] = math.floor((N+1)*h[j]/f_echantillonage)
        
    for _ in range(nb_trames):
        #On extrait une trame de N échantillons consécutifs du signal.
        trame = (S[i]*hamming(i-d_trame,N) for i in range(d_trame,f_trame))
        #On effectue une fft qu'on multiplie par son conjugué pour avoir une représentation la densité spectrale d'énergie.
        tf = np.fft.rfft(trame)
        s = tf*np.conj(tf)

        #On applique le bancs de filtres triangulaires.
        energies = []
        for j in range(1,nb_filtres-1):
            e = sum(triang(h[j-1],h[j+1],i)*s[i] for i in range(h[j-1],h[j+1]+1))
            energies.append(e)
        
        #On effectue une transformée en cosinus discrête inverse et on pondère les coefficients.
        cepstres = [0]*nb_cepstres
        for n in range(1,nb_cepstres+1):
            cepstres[n-1]=sum(math.log(energies[k])*math.cos(n*(k-0.5)*math.pi/nb_filtres) for k in range(1,nb_filtres+1))
            cepstres[n-1]*=1+13/2*math.sin(math.pi*n/13)
            
        t_mfcc.append(cepstres)
        
        
        #On calcule un analogue de la derivée temporelle première des coefficients cepstraux.
        dcepstres = [0]*nb_cepstres   
        for i in range(nb_cepstres):
            r = range(max(-dK,-i), min(dK,nb_cepstres-1))
            dcepstres[i] = sum(k*cepstres[i+k] for k in r)/(min(dK,nb_cepstres-1)-max(-dK,-i))
        
        #On calcule un analogue de derivée temporelle seconde des coefficients cepstraux.
        d2cepstres = [0]*nb_cepstres 
        for i in range(nb_cepstres):
            r = range(max(-dK,-i), min(dK,nb_cepstres-1))
            d2cepstres[i] = sum(k*dcepstres[i+k] for k in r)/(min(dK,nb_cepstres-1)-max(-dK,-i))
        
        d_mfcc.append(dcepstres)
        d2_mfcc.append(d2cepstres)
        
        d_trame += 1
        f_trame += 1
        
    return t_mfcc, d_mfcc, d2_mfcc