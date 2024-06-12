from django.db import models
from django.contrib.auth import get_user_model

User = get_user_model()

# Create your models here.
class Profile(models.Model):
  username = models.OneToOneField(User, on_delete=models.CASCADE)
  name = models.CharField(max_length=20)  
  password = models.CharField(max_length=20)
  email = models.EmailField(max_length=50)

  def __str__(self):
    return self.user.username