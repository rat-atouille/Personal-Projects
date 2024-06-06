from django.db import models

# Create your models here.
class User(models.Model):
  user_id = models.CharField(max_length=6)
  user_psw = models.CharField(max_length=13)
  #user_shelf


  def __str__(self):
    return f"{self.user_id}:{self.user_psw}"
  

class Shelf(models.Model):
    book = models.CharField(max_length=6)W