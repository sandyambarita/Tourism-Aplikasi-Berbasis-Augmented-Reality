<?php

namespace frontend\models;

use Yii;

/**
 * This is the model class for table "t_checkpoint".
 *
 * @property integer $checkpoint_id
 * @property integer $location_id
 * @property string $checkpoint_name
 *
 * @property TAnswer[] $tAnswers
 * @property TChallengeType[] $tChallengeTypes
 * @property TLocation $location
 * @property TQuestion[] $tQuestions
 */
class TCheckpoint extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 't_checkpoint';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['location_id'], 'integer'],
            [['checkpoint_name'], 'string', 'max' => 30],
            [['location_id'], 'exist', 'skipOnError' => true, 'targetClass' => TLocation::className(), 'targetAttribute' => ['location_id' => 'location_id']],
            [['latitude', 'longitude'], 'number'],
            [['path_gambarpoint'], 'string', 'max' => 50],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'checkpoint_id' => 'Checkpoint ID',
            'location_id' => 'Location ID',
            'checkpoint_name' => 'Checkpoint Name',
            'latitude' => 'Latitude',
            'longitude' => 'Longitude',
            'path_gambarpoint' => 'Path Gambar'
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTAnswers()
    {
        return $this->hasMany(TAnswer::className(), ['checkpoint_id' => 'checkpoint_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTChallengeTypes()
    {
        return $this->hasMany(TChallengeType::className(), ['checkpoint_id' => 'checkpoint_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getLocation()
    {
        return $this->hasOne(TLocation::className(), ['location_id' => 'location_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTQuestions()
    {
        return $this->hasMany(TQuestion::className(), ['checkpoint_id' => 'checkpoint_id']);
    }
}
