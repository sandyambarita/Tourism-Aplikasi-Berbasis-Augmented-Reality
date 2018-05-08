<?php

namespace frontend\models;

use Yii;

/**
 * This is the model class for table "t_about".
 *
 * @property integer $about_id
 * @property string $name
 * @property string $nim
 * @property string $prodi
 * @property string $path_gambar
 */
class TAbout extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 't_about';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['name', 'nim'], 'string', 'max' => 30],
            [['prodi'], 'string', 'max' => 50],
            [['path_gambar'], 'string', 'max' => 255],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'about_id' => 'About ID',
            'name' => 'Name',
            'nim' => 'Nim',
            'prodi' => 'Prodi',
            'path_gambar' => 'Path Gambar',
        ];
    }
}
