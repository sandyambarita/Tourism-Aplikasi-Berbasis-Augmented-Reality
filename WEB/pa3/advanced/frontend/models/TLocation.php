<?php

namespace frontend\models;

use Yii;

/**
 * This is the model class for table "t_location".
 *
 * @property integer $location_id
 * @property string $location_name
 * @property string $city_name
 *
 * @property TAnswer[] $tAnswers
 * @property TCheckpoint[] $tCheckpoints
 * @property TQuestion[] $tQuestions
 */
class TLocation extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 't_location';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['location_name', 'city_name'], 'string', 'max' => 30],
            [['path_gambar'], 'string', 'max' => 50],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'location_id' => 'Location ID',
            'location_name' => 'Location Name',
            'city_name' => 'City Name',
            'path_gambar' => 'Path Gambar',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTAnswers()
    {
        return $this->hasMany(TAnswer::className(), ['location_id' => 'location_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTCheckpoints()
    {
        return $this->hasMany(TCheckpoint::className(), ['location_id' => 'location_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTQuestions()
    {
        return $this->hasMany(TQuestion::className(), ['location_id' => 'location_id']);
    }
}
