# Generated by Django 5.0.6 on 2024-06-06 02:26

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user_id', models.CharField(max_length=6)),
                ('user_psw', models.CharField(max_length=13)),
            ],
        ),
    ]
